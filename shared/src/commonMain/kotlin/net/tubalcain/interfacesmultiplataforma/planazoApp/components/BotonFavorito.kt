package net.tubalcain.interfacesmultiplataforma.planazoApp.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

//Botón en State Hoisting

@Composable
fun BotonFavorito(
    onClick: () -> Unit,
    estrella: String ="☆"
){
    Button(onClick = onClick) {
        Text(text = estrella)
    }
}

