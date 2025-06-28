package com.todoapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.todoapp.data.database.TodoDatabase
import com.todoapp.data.entity.TodoItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class TodoDatabaseTest {
    private lateinit var database: TodoDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, TodoDatabase::class.java
        ).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveTodoItem() = runBlocking {
        val todoItem = TodoItem(
            title = "Test Todo",
            description = "Test Description",
            isCompleted = false
        )

        // Insert todo item
        val insertedId = database.todoDao().insertTodoItem(todoItem)
        assertTrue(insertedId > 0)

        // Retrieve todo items
        val todoItems = database.todoDao().getAllTodoItems().first()
        
        // Verify
        assertEquals(1, todoItems.size)
        assertEquals("Test Todo", todoItems[0].title)
        assertEquals("Test Description", todoItems[0].description)
        assertFalse(todoItems[0].isCompleted)
    }
}