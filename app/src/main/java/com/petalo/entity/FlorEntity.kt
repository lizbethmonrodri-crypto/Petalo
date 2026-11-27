
package com.petalo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flores")
data class FlorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val categoria: String,
    val precio: Double
)
