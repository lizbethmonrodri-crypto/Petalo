
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petalo.R

@Composable
fun Pantalla1(
    onCrearCuenta: () -> Unit,
    onIniciarSesion: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_beatriz),
            contentDescription = stringResource(
                R.string.logo_descripcion
            ),
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.descripcion_app),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(35.dp))

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

        Spacer(Modifier.height(15.dp))

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

        if (mostrarError) {
            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.completar_campos),
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {
                if (correo.isNotBlank() &&
                    correo.contains("@") &&
                    contrasena.isNotBlank()
                ) {
                    onIniciarSesion()
                } else {
                    mostrarError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.iniciar_sesion))
        }

        Spacer(Modifier.height(10.dp))

        OutlinedButton(
            onClick = onCrearCuenta,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.crear_cuenta))
        }
    }
}
