package com.todoapp.data.entity

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class TodoEntityTest {

    @Test
    fun `create todo item with valid title`() {
        val todo = TodoEntity(title = "Test Todo")
        
        assertEquals("Test Todo", todo.title)
        assertFalse(todo.isCompleted)
        assertNotNull(todo.createdAt)
    }

    @Test
    fun `create todo item with full details`() {
        val dueDate = LocalDateTime.now().plusDays(1)
        val todo = TodoEntity(
            title = "Complete Project",
            description = "Finish Android Todo App",
            isCompleted = false,
            dueDate = dueDate
        )

        assertEquals("Complete Project", todo.title)
        assertEquals("Finish Android Todo App", todo.description)
        assertFalse(todo.isCompleted)
        assertEquals(dueDate, todo.dueDate)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create todo item with blank title should throw exception`() {
        TodoEntity(title = "")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create todo item with whitespace title should throw exception`() {
        TodoEntity(title = "   ")
    }
}