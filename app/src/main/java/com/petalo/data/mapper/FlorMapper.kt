
package com.petalo.data.mapper

import com.petalo.entity.FlorEntity
import com.petalo.domain.model.Flor

fun FlorEntity.toDomain(): Flor {
    return Flor(
        id = id,
        nombre = nombre,
        categoria = categoria,
        precio = precio
    )
}

fun Flor.toEntity(): FlorEntity {
    return FlorEntity(
        id = id,
        nombre = nombre,
        categoria = categoria,
        precio = precio
    )
}
