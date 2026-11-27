
package com.petalo.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pedidos",
    foreignKeys = [
        ForeignKey(
            entity = FlorEntity::class,
            parentColumns = ["id"],
            childColumns = ["florId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["florId"])]
)
data class PedidoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val florId: Int,
    val cliente: String,
    val cantidad: Int
)
