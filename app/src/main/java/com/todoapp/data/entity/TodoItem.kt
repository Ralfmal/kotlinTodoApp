package com.todoapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a Todo item in the database.
 *
 * @property id Unique identifier for the todo item
 * @property title Title of the todo item
 * @property description Optional description of the todo item
 * @property isCompleted Flag indicating whether the todo item is completed
 * @property createdAt Timestamp of item creation
 */
@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)