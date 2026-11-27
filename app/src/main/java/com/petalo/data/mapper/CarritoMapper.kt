
package com.petalo.data.mapper

import com.petalo.entity.CarritoEntity
import com.petalo.domain.model.Carrito

fun CarritoEntity.toDomain(): Carrito {
    return Carrito(
        id = id,
        usuarioId = usuarioId,
        florId = florId,
        cantidad = cantidad
    )
}

fun Carrito.toEntity(): CarritoEntity {
    return CarritoEntity(
        id = id,
        usuarioId = usuarioId,
        florId = florId,
        cantidad = cantidad
    )
}
