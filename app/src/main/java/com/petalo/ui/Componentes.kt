
package com.petalo.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

data class Flor(
    @StringRes val nombre: Int,
    val precio: Int,
    @DrawableRes val imagen: Int,
    val categoria: String
)

val productos = listOf(
    Flor(
        R.string.tulipanes_mixtos,
        380,
        R.drawable.tulipanes_rojos,
        "Ramos"
    ),
    Flor(
        R.string.orquidea_maceta,
        690,
        R.drawable.orquidea_rosa,
        "Plantas"
    ),
    Flor(
        R.string.arreglo_lirios,
        520,
        R.drawable.lirios_rosas,
        "Arreglos"
    ),
    Flor(
        R.string.ramo_primavera,
        410,
        R.drawable.jardin_floral,
        "Ramos"
    )
)

@Composable
fun TarjetaProducto(
    flor: Flor,
    onAgregar: () -> Unit = {}
) {
    val nombre = stringResource(flor.nombre)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(flor.imagen),
                contentDescription = nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = nombre,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(
                    R.string.precio_mxn,
                    flor.precio
                ),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = onAgregar,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.agregar))
            }
        }
    }
}

@Composable
fun BarraInferior(
    seleccion: Int,
    onInicio: () -> Unit,
    onCatalogo: () -> Unit,
    onPerfil: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = seleccion == 0,
            onClick = onInicio,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.inicio))
            }
        )

        NavigationBarItem(
            selected = seleccion == 1,
            onClick = onCatalogo,
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.catalogo))
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            enabled = false,
            icon = {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.pedidos))
            }
        )

        NavigationBarItem(
            selected = seleccion == 3,
            onClick = onPerfil,
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.perfil))
            }
        )
    }
}
