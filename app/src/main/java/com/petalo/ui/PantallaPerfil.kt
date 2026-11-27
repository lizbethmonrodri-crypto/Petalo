
package com.petalo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.petalo.R

@Composable
fun PantallaPerfil(
    tema: String,
    cambiarTema: (String) -> Unit,
    onInicio: () -> Unit,
    onCatalogo: () -> Unit,
    onCerrarSesion: () -> Unit,
    onVenderFlores: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BarraInferior(
                seleccion = 3,
                onInicio = onInicio,
                onCatalogo = onCatalogo,
                onPerfil = {}
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo_beatriz),
                contentDescription = stringResource(
                    R.string.logo_descripcion
                ),
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit
            )

            Text(
                text = stringResource(R.string.perfil),
                style = MaterialTheme.typography.headlineMedium
            )

            Text(stringResource(R.string.usuario_cliente))

            Text(stringResource(R.string.cuenta_comprador))

            HorizontalDivider()

            Text(
                text = stringResource(R.string.seleccionar_tema),
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "Claro" to R.string.tema_claro,
                    "Oscuro" to R.string.tema_oscuro,
                    "Sistema" to R.string.tema_sistema
                ).forEach { (valor, recurso) ->
                    FilterChip(
                        selected = tema == valor,
                        onClick = { cambiarTema(valor) },
                        label = {
                            Text(stringResource(recurso))
                        }
                    )
                }
            }

            val nombreTema = when (tema) {
                "Claro" -> stringResource(R.string.tema_claro)
                "Oscuro" -> stringResource(R.string.tema_oscuro)
                else -> stringResource(R.string.tema_sistema)
            }

            Text(
                text = stringResource(
                    R.string.tema_seleccionado,
                    nombreTema
                )
            )


            Button(
                onClick = onVenderFlores,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.vender_flores))
            }


            OutlinedButton(
                onClick = onCerrarSesion,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.cerrar_sesion))
            }
        }
    }
}
