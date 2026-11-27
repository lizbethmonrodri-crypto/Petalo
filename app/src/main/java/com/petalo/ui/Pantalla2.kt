
package com.petalo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petalo.R

@Composable
fun Pantalla2(
    onRegresar: () -> Unit,
    onRegistrar: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Comprador") }
    var mostrarError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        TextButton(onClick = onRegresar) {
            Text(stringResource(R.string.regresar))
        }

        Image(
            painter = painterResource(R.drawable.logo_beatriz),
            contentDescription = stringResource(
                R.string.logo_descripcion
            ),
            modifier = Modifier
                .size(125.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.crear_cuenta),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(R.string.registro_descripcion),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mostrarError = false
            },
            label = {
                Text(stringResource(R.string.nombre))
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                mostrarError = false
            },
            label = {
                Text(stringResource(R.string.correo))
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                mostrarError = false
            },
            label = {
                Text(stringResource(R.string.contrasena))
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = stringResource(R.string.minimo_caracteres),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (mostrarError) {
            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.completar_campos),
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.tipo_cuenta),
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = tipo == "Comprador",
                onClick = { tipo = "Comprador" },
                label = {
                    Text(stringResource(R.string.comprador))
                }
            )

            FilterChip(
                selected = tipo == "Vendedor",
                onClick = { tipo = "Vendedor" },
                label = {
                    Text(stringResource(R.string.vendedor))
                }
            )
        }

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank() &&
                    correo.contains("@") &&
                    contrasena.length >= 8
                ) {
                    onRegistrar()
                } else {
                    mostrarError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.crear_cuenta))
        }

        Spacer(Modifier.height(10.dp))

        OutlinedButton(
            onClick = onRegresar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.iniciar_sesion))
        }
    }
}
