
package com.petalo.data

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petalo.ui.Pantalla1
import com.petalo.ui.Pantalla2
import com.petalo.ui.Pantalla3
import com.petalo.ui.Pantalla4
import com.petalo.ui.PantallaPerfil
import com.petalo.ui.PantallaBaseDatos

@Composable
fun AppScreens(
    tema: String,
    cambiarTema: (String) -> Unit
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // Pantalla 1: Inicio de sesión

        composable("login") {

            Pantalla1(
                onCrearCuenta = {
                    navController.navigate("registro")
                },
                onIniciarSesion = {
                    navController.navigate("inicio") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Pantalla 2: Crear cuenta

        composable("registro") {

            Pantalla2(
                onRegresar = {
                    navController.popBackStack()
                },
                onRegistrar = {
                    navController.navigate("inicio") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Pantalla 3: Inicio

        composable("inicio") {

            Pantalla3(
                onCatalogo = {
                    navController.navigate("catalogo") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                },
                onPerfil = {
                    navController.navigate("perfil") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                }
            )
        }

        // Pantalla 4: Catálogo

        composable("catalogo") {

            Pantalla4(
                onInicio = {
                    navController.navigate("inicio") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                },
                onPerfil = {
                    navController.navigate("perfil") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                }
            )
        }

        // Pantalla 5: Perfil

        composable("perfil") {

            PantallaPerfil(
                tema = tema,
                cambiarTema = cambiarTema,

                onInicio = {
                    navController.navigate("inicio") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                },

                onCatalogo = {
                    navController.navigate("catalogo") {
                        popUpTo("inicio")
                        launchSingleTop = true
                    }
                },

                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo("inicio") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },

                onVenderFlores = {
                    navController.navigate("base_datos") {
                        launchSingleTop = true
                    }
                }
            )
        }

        // Pantalla 6: Base de datos Room

        composable("base_datos") {

            PantallaBaseDatos(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }
    }
}
