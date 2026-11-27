
package com.petalo.data.repository

import com.petalo.dao.*
import com.petalo.data.mapper.*
import com.petalo.domain.model.*
import com.petalo.domain.repository.PetaloRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

class PetaloRepositoryImpl(
    private val florDao: FlorDao,
    private val pedidoDao: PedidoDao,
    private val usuarioDao: UsuarioDao,
    private val carritoDao: CarritoDao
) : PetaloRepository {

    // FLORES

    override fun obtenerFlores(): Flow<List<Flor>> {
        return florDao.obtenerTodas().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun insertarFlor(flor: Flor) {
        florDao.insertar(flor.toEntity())
    }

    override suspend fun actualizarFlor(flor: Flor) {
        florDao.actualizar(flor.toEntity())
    }

    override suspend fun eliminarFlor(flor: Flor) {
        florDao.eliminar(flor.toEntity())
    }


    // PEDIDOS

    override fun obtenerPedidos(): Flow<List<Pedido>> {
        return pedidoDao.obtenerTodos().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun insertarPedido(pedido: Pedido) {
        pedidoDao.insertar(pedido.toEntity())
    }

    override suspend fun actualizarPedido(pedido: Pedido) {
        pedidoDao.actualizar(pedido.toEntity())
    }

    override suspend fun eliminarPedido(pedido: Pedido) {
        pedidoDao.eliminar(pedido.toEntity())
    }


    // USUARIOS

    override fun obtenerUsuarios(): Flow<List<Usuario>> {
        return usuarioDao.obtenerTodos().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun insertarUsuario(
        usuario: Usuario,
        passwordHash: String
    ) {
        usuarioDao.insertar(
            usuario.toEntity(passwordHash)
        )
    }

    override suspend fun actualizarUsuario(usuario: Usuario) {

        val actual = usuarioDao.obtenerTodos()
            .first()
            .find { it.id == usuario.id }

        if (actual != null) {
            usuarioDao.actualizar(
                usuario.toEntity(actual.passwordHash)
            )
        }
    }

    override suspend fun eliminarUsuario(usuario: Usuario) {

        val actual = usuarioDao.obtenerTodos()
            .first()
            .find { it.id == usuario.id }

        if (actual != null) {
            usuarioDao.eliminar(actual)
        }
    }


    // CARRITO

    override fun obtenerCarrito(): Flow<List<Carrito>> {
        return carritoDao.obtenerTodos().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun insertarCarrito(carrito: Carrito) {
        carritoDao.insertar(carrito.toEntity())
    }

    override suspend fun actualizarCarrito(carrito: Carrito) {
        carritoDao.actualizar(carrito.toEntity())
    }

    override suspend fun eliminarCarrito(carrito: Carrito) {
        carritoDao.eliminar(carrito.toEntity())
    }
}
