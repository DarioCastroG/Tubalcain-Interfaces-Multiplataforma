package net.tubalcain.interfacesmultiplataforma

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.Boolean

//Muestra una tarjeta con esta función en vez del EventoCard cuando el evento esté destacado
@Composable
fun EventoDestacado(
    evento : Evento,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    mostrarDescripcion: Boolean = false
){
    val tarjeta = Color(0xFFFFA200)
    val texto = Color(0xFF18427A)

    Card(
        modifier = modifier
            .widthIn(max = 850.dp)
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = tarjeta
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = evento.emoji,
                fontSize = 32.sp
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Uso la cabecera seccion aquí para poder mostrar el título y el subtítulo del evento
                CabeceraSeccion(
                    titulo = evento.nombre,
                    subtitulo = evento.subtitulo,
                )

                Text(
                    text = evento.categoria,
                    color = texto
                )

                Text(
                    text = evento.fecha,
                    color = Color.LightGray
                )
                AnimatedVisibility(
                    visible = mostrarDescripcion
                ) {
                    Text(
                        text = evento.descripcion,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Button(
                onClick = onClick
            ){
                Text(
                    text= "Ver evento",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}