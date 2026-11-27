
package com.petalo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petalo.domain.model.*
import com.petalo.domain.repository.PetaloRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetaloViewModel @Inject constructor(
    private val repository: PetaloRepository
) : ViewModel() {

    val flores = repository.obtenerFlores()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val pedidos = repository.obtenerPedidos()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val usuarios = repository.obtenerUsuarios()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val carrito = repository.obtenerCarrito()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // FLORES

    fun insertarFlor(flor: Flor) {
        viewModelScope.launch {
            repository.insertarFlor(flor)
        }
    }

    fun actualizarFlor(flor: Flor) {
        viewModelScope.launch {
            repository.actualizarFlor(flor)
        }
    }

    fun eliminarFlor(flor: Flor) {
        viewModelScope.launch {
            repository.eliminarFlor(flor)
        }
    }

    // PEDIDOS

    fun insertarPedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.insertarPedido(pedido)
        }
    }

    fun actualizarPedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.actualizarPedido(pedido)
        }
    }

    fun eliminarPedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.eliminarPedido(pedido)
        }
    }

    // USUARIOS

    fun insertarUsuario(
        usuario: Usuario,
        passwordHash: String
    ) {
        viewModelScope.launch {
            repository.insertarUsuario(
                usuario,
                passwordHash
            )
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.actualizarUsuario(usuario)
        }
    }

    fun eliminarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.eliminarUsuario(usuario)
        }
    }

    // CARRITO

    fun insertarCarrito(carrito: Carrito) {
        viewModelScope.launch {
            repository.insertarCarrito(carrito)
        }
    }

    fun actualizarCarrito(carrito: Carrito) {
        viewModelScope.launch {
            repository.actualizarCarrito(carrito)
        }
    }

    fun eliminarCarrito(carrito: Carrito) {
        viewModelScope.launch {
            repository.eliminarCarrito(carrito)
        }
    }
}
