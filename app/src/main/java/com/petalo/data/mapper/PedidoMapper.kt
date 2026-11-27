
package com.petalo.data.mapper

import com.petalo.entity.PedidoEntity
import com.petalo.domain.model.Pedido

fun PedidoEntity.toDomain(): Pedido {
    return Pedido(
        id = id,
        florId = florId,
        cliente = cliente,
        cantidad = cantidad
    )
}

fun Pedido.toEntity(): PedidoEntity {
    return PedidoEntity(
        id = id,
        florId = florId,
        cliente = cliente,
        cantidad = cantidad
    )
}
