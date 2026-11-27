
package com.petalo.domain.model

data class Pedido(
    val id: Int = 0,
    val florId: Int,
    val cliente: String,
    val cantidad: Int
)
