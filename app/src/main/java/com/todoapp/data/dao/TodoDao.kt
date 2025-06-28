package com.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.todoapp.data.entity.TodoItem
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Todo items.
 * Defines database operations for TodoItem.
 */
@Dao
interface TodoDao {
    /**
     * Retrieve all todo items.
     *
     * @return Flow of list of TodoItems
     */
    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): Flow<List<TodoItem>>

    /**
     * Insert a new todo item.
     *
     * @param todoItem TodoItem to be inserted
     * @return Long ID of the inserted item
     */
    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem): Long
}