
package com.petalo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petalo.R

@Composable
fun Pantalla4(
    onInicio: () -> Unit,
    onPerfil: () -> Unit
) {
    var buscar by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Todos") }
    var ascendente by remember { mutableStateOf(true) }

    val nombres = productos.map {
        stringResource(it.nombre)
    }

    val filtrados = productos.filterIndexed { index, flor ->
        (categoria == "Todos" || flor.categoria == categoria) &&
                nombres[index].contains(buscar, ignoreCase = true)
    }.let { lista ->
        if (ascendente) {
            lista.sortedBy { it.precio }
        } else {
            lista.sortedByDescending { it.precio }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BarraInferior(
                seleccion = 1,
                onInicio = onInicio,
                onCatalogo = {},
                onPerfil = onPerfil
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.catalogo),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                value = buscar,
                onValueChange = { buscar = it },
                placeholder = {
                    Text(stringResource(R.string.buscar_flores))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                listOf(
                    "Todos" to R.string.todos,
                    "Ramos" to R.string.ramos,
                    "Plantas" to R.string.plantas,
                    "Arreglos" to R.string.arreglos
                ).forEach { (valor, recurso) ->
                    FilterChip(
                        selected = categoria == valor,
                        onClick = { categoria = valor },
                        label = {
                            Text(
                                text = stringResource(recurso),
                                fontSize = 11.sp
                            )
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(
                        R.string.cantidad_productos,
                        filtrados.size
                    )
                )

                TextButton(
                    onClick = { ascendente = !ascendente }
                ) {
                    Text(
                        if (ascendente) {
                            stringResource(R.string.precio_ascendente)
                        } else {
                            stringResource(R.string.precio_descendente)
                        }
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            if (filtrados.isEmpty()) {
                Text(stringResource(R.string.no_productos))
            }

            filtrados.chunked(2).forEach { fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {
                    fila.forEach { flor ->
                        Box(Modifier.weight(1f)) {
                            TarjetaProducto(flor)
                        }
                    }

                    if (fila.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
