package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_states")
data class NameState(
    @PrimaryKey val nameId: Int, // Represents 1 to 99
    val isFavorite: Boolean = false,
    val personalNote: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)
