
package com.petalo.domain.repository

import com.petalo.domain.model.*
import kotlinx.coroutines.flow.Flow

interface PetaloRepository {

    // FLORES

    fun obtenerFlores(): Flow<List<Flor>>

    suspend fun insertarFlor(flor: Flor)

    suspend fun actualizarFlor(flor: Flor)

    suspend fun eliminarFlor(flor: Flor)


    // PEDIDOS

    fun obtenerPedidos(): Flow<List<Pedido>>

    suspend fun insertarPedido(pedido: Pedido)

    suspend fun actualizarPedido(pedido: Pedido)

    suspend fun eliminarPedido(pedido: Pedido)


    // USUARIOS

    fun obtenerUsuarios(): Flow<List<Usuario>>

    suspend fun insertarUsuario(
        usuario: Usuario,
        passwordHash: String
    )

    suspend fun actualizarUsuario(usuario: Usuario)

    suspend fun eliminarUsuario(usuario: Usuario)


    // CARRITO

    fun obtenerCarrito(): Flow<List<Carrito>>

    suspend fun insertarCarrito(carrito: Carrito)

    suspend fun actualizarCarrito(carrito: Carrito)

    suspend fun eliminarCarrito(carrito: Carrito)
}
