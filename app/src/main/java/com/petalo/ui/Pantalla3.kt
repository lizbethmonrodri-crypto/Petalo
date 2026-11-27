
package com.petalo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petalo.R

@Composable
fun Pantalla3(
    onCatalogo: () -> Unit,
    onPerfil: () -> Unit
) {
    var buscar by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Ramos") }

    val destacados = listOf(
        Flor(
            R.string.ramo_rosas,
            450,
            R.drawable.ramo_rosas_rojas,
            "Ramos"
        ),
        Flor(
            R.string.ramo_girasoles,
            320,
            R.drawable.ramo_girasoles,
            "Ramos"
        )
    )

    val nombres = destacados.map {
        stringResource(it.nombre)
    }

    val visibles = destacados.filterIndexed { index, flor ->
        nombres[index].contains(buscar, ignoreCase = true) &&
                flor.categoria == categoria
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BarraInferior(
                seleccion = 0,
                onInicio = {},
                onCatalogo = onCatalogo,
                onPerfil = onPerfil
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(18.dp)
        ) {
            Text(
                text = stringResource(R.string.inicio),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Image(
                        painter = painterResource(
                            R.drawable.jardin_floral
                        ),
                        contentDescription = stringResource(
                            R.string.ramo_primavera
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(145.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = stringResource(
                            R.string.flores_frescas
                        ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Text(
                        text = stringResource(
                            R.string.descripcion_promocion
                        ),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    Spacer(Modifier.height(10.dp))

                    Button(onClick = onCatalogo) {
                        Text(stringResource(R.string.ver_catalogo))
                    }
                }
            }

            Spacer(Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "Ramos" to R.string.ramos,
                    "Plantas" to R.string.plantas,
                    "Arreglos" to R.string.arreglos
                ).forEach { (valor, recurso) ->
                    FilterChip(
                        selected = categoria == valor,
                        onClick = { categoria = valor },
                        label = {
                            Text(stringResource(recurso))
                        }
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.destacados),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                TextButton(onClick = onCatalogo) {
                    Text(stringResource(R.string.ver_todos))
                }
            }

            Spacer(Modifier.height(10.dp))

            if (visibles.isEmpty()) {
                Text(stringResource(R.string.no_flores))
            } else {
                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {
                    visibles.forEach { flor ->
                        Box(Modifier.weight(1f)) {
                            TarjetaProducto(flor)
                        }
                    }
                }
            }
        }
    }
}
