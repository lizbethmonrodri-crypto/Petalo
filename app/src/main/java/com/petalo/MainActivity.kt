
package com.petalo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.petalo.data.AppScreens
import com.petalo.ui.theme.PetaloTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var tema by remember {
                mutableStateOf("Sistema")
            }

            PetaloTheme(tema = tema) {
                AppScreens(
                    tema = tema,
                    cambiarTema = { tema = it }
                )
            }
        }
    }
}
