
package com.petalo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColoresClaros = lightColorScheme(
    primary = RosaPrincipal,
    secondary = RosaClaro,
    background = FondoClaro,
    surface = Blanco,
    onPrimary = Blanco,
    onBackground = TextoOscuro,
    onSurface = TextoOscuro,
    secondaryContainer = RosaClaro,
    onSecondaryContainer = TextoOscuro
)

private val ColoresOscuros = darkColorScheme(
    primary = RosaOscuro,
    secondary = RosaPrincipal,
    background = FondoOscuro,
    surface = SuperficieOscura,
    onPrimary = FondoOscuro,
    onBackground = Blanco,
    onSurface = Blanco,
    secondaryContainer = SuperficieOscura,
    onSecondaryContainer = Blanco
)

@Composable
fun PetaloTheme(
    tema: String = "Sistema",
    content: @Composable () -> Unit
) {
    val oscuro = when (tema) {
        "Claro" -> false
        "Oscuro" -> true
        else -> isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if (oscuro) {
            ColoresOscuros
        } else {
            ColoresClaros
        },
        content = content
    )
}
