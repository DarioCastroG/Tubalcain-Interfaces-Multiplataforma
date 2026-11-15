package net.tubalcain.interfacesmultiplataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//Cabecera de un evento con el título y el subtítulo de este (el subtítulo tiene un valor por defecto)
@Composable
fun CabeceraSeccion(
    titulo: String,
    subtitulo: String = "Subtítulo del evento",
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Text(
            text = titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = subtitulo,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}