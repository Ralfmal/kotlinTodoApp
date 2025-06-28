package com.todoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.todoapp.data.dao.TodoDao
import com.todoapp.data.entity.TodoItem

/**
 * Room Database for storing Todo items.
 * This is a singleton database instance to ensure only one connection is maintained.
 */
@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    // Abstract method to get the TodoDao
    abstract fun todoDao(): TodoDao

    companion object {
        // Volatile ensures the instance is always up-to-date across threads
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        /**
         * Get or create the database instance.
         * Uses synchronized block to ensure thread safety during instance creation.
         *
         * @param context Application context
         * @return TodoDatabase instance
         */
        fun getDatabase(context: Context): TodoDatabase {
            // If instance already exists, return it
            return INSTANCE ?: synchronized(this) {
                // Double-checked locking pattern
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        /**
         * Create the Room database instance.
         *
         * @param context Application context
         * @return Created TodoDatabase
         */
        private fun buildDatabase(context: Context): TodoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "todo_database"
            )
            // Optional: Add migration strategy if needed in future
            .fallbackToDestructiveMigration() // Simplified for this example
            .build()
        }
    }
}