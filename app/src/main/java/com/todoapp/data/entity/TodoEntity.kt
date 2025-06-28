package com.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Represents a Todo item in the Room database.
 * Defines the schema for storing todo items with their properties.
 *
 * @property id Unique identifier for the todo item
 * @property title Title of the todo item
 * @property description Optional description of the todo item
 * @property isCompleted Flag indicating whether the todo item is completed
 * @property createdAt Timestamp of when the todo item was created
 * @property dueDate Optional due date for the todo item
 */
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "due_date")
    val dueDate: LocalDateTime? = null
) {
    // Validate title is not blank during object creation
    init {
        require(title.isNotBlank()) { "Todo title cannot be blank" }
    }
}