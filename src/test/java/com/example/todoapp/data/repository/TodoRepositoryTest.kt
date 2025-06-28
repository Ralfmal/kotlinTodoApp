package com.example.todoapp.data.repository

import com.example.todoapp.data.dao.TodoDao
import com.example.todoapp.data.model.Todo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TodoRepositoryTest {
    private val todoDao = mockk<TodoDao>()
    private val todoRepository = TodoRepository(todoDao)

    @Test
    fun `addTodo should save todo item successfully`() = runBlocking {
        // Arrange
        val todo = Todo(title = "Test Todo")
        coEvery { todoDao.insertTodo(todo) } returns 1L

        // Act
        val result = todoRepository.addTodo(todo)

        // Assert
        assertEquals(1L, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `addTodo should throw exception for blank title`() = runBlocking {
        // Arrange
        val todo = Todo(title = "")

        // Act & Assert
        todoRepository.addTodo(todo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `addTodo should throw exception for title exceeding max length`() = runBlocking {
        // Arrange
        val longTitle = "a".repeat(101)
        val todo = Todo(title = longTitle)

        // Act & Assert
        todoRepository.addTodo(todo)
    }
}