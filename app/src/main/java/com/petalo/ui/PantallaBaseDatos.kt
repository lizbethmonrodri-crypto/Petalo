
package com.petalo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import com.petalo.domain.model.Flor
import com.petalo.domain.model.Pedido
import com.petalo.domain.model.Usuario
import com.petalo.domain.model.Carrito
import com.petalo.ui.viewmodel.PetaloViewModel
import java.security.MessageDigest

@Composable
fun PantallaBaseDatos(onRegresar: () -> Unit) {

    val viewModel: PetaloViewModel = hiltViewModel()

    val flores by viewModel.flores.collectAsStateWithLifecycle()
    val pedidos by viewModel.pedidos.collectAsStateWithLifecycle()
    val usuarios by viewModel.usuarios.collectAsStateWithLifecycle()
    val carrito by viewModel.carrito.collectAsStateWithLifecycle()

    var pestana by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextButton(onClick = onRegresar) {
            Text("← Regresar al perfil")
        }

        Text(
            "Administración de Pétalo",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        TabRow(selectedTabIndex = pestana) {

            listOf(
                "Flores",
                "Pedidos",
                "Usuarios",
                "Carrito"
            ).forEachIndexed { indice, titulo ->

                Tab(
                    selected = pestana == indice,
                    onClick = { pestana = indice },
                    text = { Text(titulo) }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        when (pestana) {

            0 -> FormularioFlores(
                flores = flores,
                guardar = { flor, editar ->
                    if (editar) {
                        viewModel.actualizarFlor(flor)
                    } else {
                        viewModel.insertarFlor(flor)
                    }
                },
                eliminar = {
                    viewModel.eliminarFlor(it)
                }
            )

            1 -> FormularioPedidos(
                flores = flores,
                pedidos = pedidos,
                guardar = { pedido, editar ->
                    if (editar) {
                        viewModel.actualizarPedido(pedido)
                    } else {
                        viewModel.insertarPedido(pedido)
                    }
                },
                eliminar = {
                    viewModel.eliminarPedido(it)
                }
            )

            2 -> FormularioUsuarios(
                usuarios = usuarios,
                guardar = { usuario, password, editar ->
                    if (editar) {
                        viewModel.actualizarUsuario(usuario)
                    } else {
                        viewModel.insertarUsuario(usuario, password)
                    }
                },
                eliminar = {
                    viewModel.eliminarUsuario(it)
                }
            )

            3 -> FormularioCarrito(
                usuarios = usuarios,
                flores = flores,
                carrito = carrito,
                guardar = { producto, editar ->
                    if (editar) {
                        viewModel.actualizarCarrito(producto)
                    } else {
                        viewModel.insertarCarrito(producto)
                    }
                },
                eliminar = {
                    viewModel.eliminarCarrito(it)
                }
            )
        }
    }
}

// FORMULARIO DE FLORES

@Composable
fun FormularioFlores(
    flores: List<Flor>,
    guardar: (Flor, Boolean) -> Unit,
    eliminar: (Flor) -> Unit
) {

    var id by remember { mutableIntStateOf(0) }
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var florEliminar by remember {
        mutableStateOf<Flor?>(null)
    }

    fun limpiar() {
        id = 0
        nombre = ""
        categoria = ""
        precio = ""
        error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            if (id == 0) "Registrar flor" else "Editar flor",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la flor") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {

                val numero = precio.toDoubleOrNull()

                if (nombre.isBlank()) {

                    error = "Ingresa el nombre de la flor"

                } else if (categoria.isBlank()) {

                    error = "Ingresa una categoría"

                } else if (
                    numero == null ||
                    !numero.isFinite() ||
                    numero <= 0
                ) {

                    error = "Ingresa un precio válido mayor a cero"

                } else {

                    guardar(
                        Flor(
                            id = id,
                            nombre = nombre.trim(),
                            categoria = categoria.trim(),
                            precio = numero
                        ),
                        id != 0
                    )

                    limpiar()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (id == 0) "Guardar flor"
                else "Actualizar flor"
            )
        }

        if (id != 0) {
            TextButton(onClick = { limpiar() }) {
                Text("Cancelar edición")
            }
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        Text(
            "Flores registradas: ${flores.size}",
            style = MaterialTheme.typography.titleMedium
        )

        if (flores.isEmpty()) {
            Text("No hay flores registradas")
        }

        flores.forEach { flor ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text(flor.nombre)
                    Text("ID: ${flor.id}")
                    Text("Categoría: ${flor.categoria}")
                    Text("Precio: $${flor.precio} MXN")

                    Row {

                        TextButton(
                            onClick = {
                                id = flor.id
                                nombre = flor.nombre
                                categoria = flor.categoria
                                precio = flor.precio.toString()
                                error = ""
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                florEliminar = flor
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }

    florEliminar?.let { flor ->

        ConfirmarEliminacion(
            titulo = "Eliminar flor",
            mensaje = "¿Deseas eliminar ${flor.nombre}? También podrían eliminarse sus registros relacionados.",
            cancelar = {
                florEliminar = null
            },
            confirmar = {

                eliminar(flor)

                if (id == flor.id) {
                    limpiar()
                }

                florEliminar = null
            }
        )
    }
}

// FORMULARIO DE PEDIDOS

@Composable
fun FormularioPedidos(
    flores: List<Flor>,
    pedidos: List<Pedido>,
    guardar: (Pedido, Boolean) -> Unit,
    eliminar: (Pedido) -> Unit
) {

    var id by remember { mutableIntStateOf(0) }
    var florId by remember { mutableStateOf("") }
    var cliente by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var pedidoEliminar by remember {
        mutableStateOf<Pedido?>(null)
    }

    fun limpiar() {
        id = 0
        florId = ""
        cliente = ""
        cantidad = ""
        error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            if (id == 0) "Registrar pedido"
            else "Editar pedido",
            style = MaterialTheme.typography.titleMedium
        )

        Text("Flores disponibles:")

        flores.forEach { flor ->
            Text("ID ${flor.id}: ${flor.nombre}")
        }

        if (flores.isEmpty()) {
            Text("Primero registra una flor")
        }

        OutlinedTextField(
            value = florId,
            onValueChange = { florId = it },
            label = { Text("ID de la flor") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cliente,
            onValueChange = { cliente = it },
            label = { Text("Nombre del cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {

                val numeroFlor = florId.toIntOrNull()
                val numeroCantidad = cantidad.toIntOrNull()

                if (
                    numeroFlor == null ||
                    flores.none { it.id == numeroFlor }
                ) {

                    error = "El ID de la flor no existe"

                } else if (cliente.isBlank()) {

                    error = "Ingresa el nombre del cliente"

                } else if (
                    numeroCantidad == null ||
                    numeroCantidad <= 0
                ) {

                    error = "La cantidad debe ser mayor a cero"

                } else {

                    guardar(
                        Pedido(
                            id = id,
                            florId = numeroFlor,
                            cliente = cliente.trim(),
                            cantidad = numeroCantidad
                        ),
                        id != 0
                    )

                    limpiar()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (id == 0) "Guardar pedido"
                else "Actualizar pedido"
            )
        }

        if (id != 0) {
            TextButton(onClick = { limpiar() }) {
                Text("Cancelar edición")
            }
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        Text(
            "Pedidos registrados: ${pedidos.size}",
            style = MaterialTheme.typography.titleMedium
        )

        if (pedidos.isEmpty()) {
            Text("No hay pedidos registrados")
        }

        pedidos.forEach { pedido ->

            val flor = flores.find {
                it.id == pedido.florId
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text(pedido.cliente)
                    Text("Pedido ID: ${pedido.id}")
                    Text("Flor: ${flor?.nombre ?: "No encontrada"}")
                    Text("Cantidad: ${pedido.cantidad}")

                    Row {

                        TextButton(
                            onClick = {
                                id = pedido.id
                                florId = pedido.florId.toString()
                                cliente = pedido.cliente
                                cantidad = pedido.cantidad.toString()
                                error = ""
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                pedidoEliminar = pedido
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }

    pedidoEliminar?.let { pedido ->

        ConfirmarEliminacion(
            titulo = "Eliminar pedido",
            mensaje = "¿Deseas eliminar el pedido de ${pedido.cliente}?",
            cancelar = {
                pedidoEliminar = null
            },
            confirmar = {

                eliminar(pedido)

                if (id == pedido.id) {
                    limpiar()
                }

                pedidoEliminar = null
            }
        )
    }
}

// FORMULARIO DE USUARIOS

@Composable
fun FormularioUsuarios(
    usuarios: List<Usuario>,
    guardar: (Usuario, String, Boolean) -> Unit,
    eliminar: (Usuario) -> Unit
) {

    var id by remember { mutableIntStateOf(0) }
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("Cliente") }
    var error by remember { mutableStateOf("") }

    var usuarioEliminar by remember {
        mutableStateOf<Usuario?>(null)
    }

    fun limpiar() {
        id = 0
        nombre = ""
        correo = ""
        password = ""
        rol = "Cliente"
        error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            if (id == 0) "Registrar usuario"
            else "Editar usuario",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        if (id == 0) {

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(8.dp))

        Text("Tipo de usuario")

        Row {

            FilterChip(
                selected = rol == "Cliente",
                onClick = { rol = "Cliente" },
                label = { Text("Cliente") }
            )

            Spacer(Modifier.width(8.dp))

            FilterChip(
                selected = rol == "Vendedor",
                onClick = { rol = "Vendedor" },
                label = { Text("Vendedor") }
            )
        }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {

                error = when {

                    nombre.isBlank() ->
                        "Ingresa el nombre del usuario"

                    !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(correo.trim()).matches() ->
                        "Ingresa un correo electrónico válido"

                    usuarios.any {
                        it.correo.equals(
                            correo.trim(),
                            ignoreCase = true
                        ) && it.id != id
                    } ->
                        "El correo ya está registrado"

                    id == 0 && password.length < 8 ->
                        "La contraseña debe tener mínimo 8 caracteres"

                    else -> ""
                }

                if (error.isEmpty()) {

                    val usuario = Usuario(
                        id = id,
                        nombre = nombre.trim(),
                        correo = correo.trim(),
                        rol = rol
                    )

                    val hash = if (id == 0) {
                        generarHashPetalo(password)
                    } else {
                        ""
                    }

                    guardar(
                        usuario,
                        hash,
                        id != 0
                    )

                    limpiar()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (id == 0) "Guardar usuario"
                else "Actualizar usuario"
            )
        }

        if (id != 0) {
            TextButton(onClick = { limpiar() }) {
                Text("Cancelar edición")
            }
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        Text(
            "Usuarios registrados: ${usuarios.size}",
            style = MaterialTheme.typography.titleMedium
        )

        if (usuarios.isEmpty()) {
            Text("No hay usuarios registrados")
        }

        usuarios.forEach { usuario ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text("ID: ${usuario.id}")
                    Text("Nombre: ${usuario.nombre}")
                    Text("Correo: ${usuario.correo}")
                    Text("Rol: ${usuario.rol}")

                    Row {

                        TextButton(
                            onClick = {
                                id = usuario.id
                                nombre = usuario.nombre
                                correo = usuario.correo
                                rol = usuario.rol
                                password = ""
                                error = ""
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                usuarioEliminar = usuario
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }

    usuarioEliminar?.let { usuario ->

        ConfirmarEliminacion(
            titulo = "Eliminar usuario",
            mensaje = "¿Deseas eliminar a ${usuario.nombre}? También podrían eliminarse sus registros relacionados.",
            cancelar = {
                usuarioEliminar = null
            },
            confirmar = {

                eliminar(usuario)

                if (id == usuario.id) {
                    limpiar()
                }

                usuarioEliminar = null
            }
        )
    }
}

// FORMULARIO DE CARRITO

@Composable
fun FormularioCarrito(
    usuarios: List<Usuario>,
    flores: List<Flor>,
    carrito: List<Carrito>,
    guardar: (Carrito, Boolean) -> Unit,
    eliminar: (Carrito) -> Unit
) {

    var id by remember { mutableIntStateOf(0) }
    var usuarioId by remember { mutableStateOf("") }
    var florId by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var productoEliminar by remember {
        mutableStateOf<Carrito?>(null)
    }

    fun limpiar() {
        id = 0
        usuarioId = ""
        florId = ""
        cantidad = ""
        error = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            if (id == 0) "Agregar al carrito"
            else "Editar carrito",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Text("Usuarios disponibles:")

        usuarios.forEach { usuario ->
            Text("ID ${usuario.id}: ${usuario.nombre}")
        }

        if (usuarios.isEmpty()) {
            Text("Primero registra un usuario")
        }

        Spacer(Modifier.height(8.dp))

        Text("Flores disponibles:")

        flores.forEach { flor ->
            Text("ID ${flor.id}: ${flor.nombre}")
        }

        if (flores.isEmpty()) {
            Text("Primero registra una flor")
        }

        OutlinedTextField(
            value = usuarioId,
            onValueChange = { usuarioId = it },
            label = { Text("ID del usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = florId,
            onValueChange = { florId = it },
            label = { Text("ID de la flor") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {

                val numeroUsuario = usuarioId.toIntOrNull()
                val numeroFlor = florId.toIntOrNull()
                val numeroCantidad = cantidad.toIntOrNull()

                if (
                    numeroUsuario == null ||
                    usuarios.none { it.id == numeroUsuario }
                ) {

                    error = "El ID del usuario no existe"

                } else if (
                    numeroFlor == null ||
                    flores.none { it.id == numeroFlor }
                ) {

                    error = "El ID de la flor no existe"

                } else if (
                    numeroCantidad == null ||
                    numeroCantidad <= 0
                ) {

                    error = "Ingresa una cantidad mayor a cero"

                } else {

                    guardar(
                        Carrito(
                            id = id,
                            usuarioId = numeroUsuario,
                            florId = numeroFlor,
                            cantidad = numeroCantidad
                        ),
                        id != 0
                    )

                    limpiar()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (id == 0) "Guardar en carrito"
                else "Actualizar carrito"
            )
        }

        if (id != 0) {
            TextButton(onClick = { limpiar() }) {
                Text("Cancelar edición")
            }
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        Text(
            "Productos registrados: ${carrito.size}",
            style = MaterialTheme.typography.titleMedium
        )

        if (carrito.isEmpty()) {
            Text("El carrito está vacío")
        }

        carrito.forEach { producto ->

            val usuario = usuarios.find {
                it.id == producto.usuarioId
            }

            val flor = flores.find {
                it.id == producto.florId
            }

            val total =
                (flor?.precio ?: 0.0) * producto.cantidad

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {

                Column(Modifier.padding(12.dp)) {

                    Text("ID: ${producto.id}")
                    Text("Usuario: ${usuario?.nombre ?: "No encontrado"}")
                    Text("Flor: ${flor?.nombre ?: "No encontrada"}")
                    Text("Cantidad: ${producto.cantidad}")
                    Text("Total: $${total} MXN")

                    Row {

                        TextButton(
                            onClick = {

                                id = producto.id
                                usuarioId = producto.usuarioId.toString()
                                florId = producto.florId.toString()
                                cantidad = producto.cantidad.toString()
                                error = ""
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                productoEliminar = producto
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }

    productoEliminar?.let { producto ->

        ConfirmarEliminacion(
            titulo = "Eliminar producto",
            mensaje = "¿Deseas eliminar este producto del carrito?",
            cancelar = {
                productoEliminar = null
            },
            confirmar = {

                eliminar(producto)

                if (id == producto.id) {
                    limpiar()
                }

                productoEliminar = null
            }
        )
    }
}

// CONFIRMACION DE ELIMINACION

@Composable
private fun ConfirmarEliminacion(
    titulo: String,
    mensaje: String,
    cancelar: () -> Unit,
    confirmar: () -> Unit
) {

    AlertDialog(
        onDismissRequest = cancelar,

        title = {
            Text(titulo)
        },

        text = {
            Text(mensaje)
        },

        confirmButton = {

            TextButton(onClick = confirmar) {
                Text(
                    "Eliminar",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },

        dismissButton = {

            TextButton(onClick = cancelar) {
                Text("Cancelar")
            }
        }
    )
}

// HASH DE CONTRASENA PARA LA PRACTICA

private fun generarHashPetalo(password: String): String {

    return MessageDigest
        .getInstance("SHA-256")
        .digest(password.toByteArray())
        .joinToString("") {
            "%02x".format(it.toInt() and 0xff)
        }
}
