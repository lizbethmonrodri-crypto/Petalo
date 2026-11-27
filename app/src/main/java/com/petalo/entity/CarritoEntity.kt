
package com.petalo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "carrito",
    foreignKeys = [
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FlorEntity::class,
            parentColumns = ["id"],
            childColumns = ["florId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["usuarioId"]),
        Index(value = ["florId"]),
        Index(
            value = ["usuarioId", "florId"],
            unique = true
        )
    ]
)
data class CarritoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuarioId: Int,
    val florId: Int,
    val cantidad: Int
)
