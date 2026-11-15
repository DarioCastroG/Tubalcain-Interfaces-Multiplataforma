package net.tubalcain.interfacesmultiplataforma

import BotonLimpiarFiltros
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun PlanazoAppTarea3() {

    val fondo = Color(0xFF101018)
    val morado = Color(0xFFB5A2FF)

    //He modificado la clase evento para que pueda tener un subtítulo con valor por defecto
    val eventos = remember {
        listOf(
            Evento(
                "Festival Urbano",
                "Música",
                "Sábado · 20:00",
                "🎤",
                "Festival de música urbana."
            ),
            Evento(
                "Torneo EA FC",
                "Gaming",
                "Viernes · 17:00",
                "🎮",
                "Competición de fútbol virtual.",
                "Torneo de EA Sports FC 27"
            ),
            Evento(
                "Noche de Streamers",
                "Creadores",
                "Viernes · 19:30",
                "📺",
                "Encuentro de creadores de contenido.",
                "ft. ElRichMC"
            ),
            Evento(
                "Batalla de DJs",
                "Música",
                "Sábado · 22:00",
                "🎧",
                "Sesión de música y DJs."
            ),
            Evento(
                "Torneo Minecraft",
                "Gaming",
                "Domingo · 18:00",
                "🏗️",
                "Retos de construcción por equipos.",
                "Torneo PVP de Skywars"
            )
        )
    }

    // ESTADOS
    var busqueda by remember { mutableStateOf("") }
    var categoriaElegida by remember { mutableStateOf("Todos") }
    var mostrarDescripciones by remember { mutableStateOf(false) }

    // NAVEGACIÓN
    var pantalla by remember { mutableStateOf("lista") }
    var eventoSeleccionado by remember {
        mutableStateOf<Evento?>(null)
    }

    // FORMULARIO
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var inscrito by remember { mutableStateOf(false) }

    // FILTRADO
    val eventosFiltrados = eventos.filter { evento ->
        evento.nombre.contains(busqueda, ignoreCase = true) &&
                (categoriaElegida == "Todos" ||
                        evento.categoria == categoriaElegida)
    }.sortedByDescending{ evento -> evento.destacado }
    //Si un evento está destacado, aparecerá antes. Uso sortedByDescending porque kotlin
    //ordena de menor a mayor por defecto (siendo false (0) menor que true (1))

    //Nota: si el evento está destacado de antes y no se pone al principio de la cola,
    //pruebe a cambiar de categoría elegida y después vuelva a poner "Todos"

    // SNACKBAR
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    MaterialTheme {

        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            containerColor = fondo,
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(fondo)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "🎟️ PLANAZO",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "DAM2",
                        color = morado
                    )
                }
            }

        ) { padding ->

            // La LazyColumn ocupa toda la pantalla.
            // El contenido se limita a 850.dp.
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (pantalla == "lista") {

                    // TÍTULO
                    item {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                "¿Qué plan tienes?",
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Descubre eventos que no te puedes perder",
                                color = Color.LightGray
                            )
                        }
                    }

                    // BUSCADOR
                    item {
                        BuscadorEventos(
                            texto = busqueda,
                            onTextoChange = { nuevoTexto ->
                                busqueda = nuevoTexto
                            },
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        )
                    }

                    // FILTROS POR CATEGORÍA
                    item {
                        Row(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "Todos",
                                "Música",
                                "Gaming",
                                "Creadores"
                            ).forEach { categoria ->

                                CategoriaChip(
                                    categoria = categoria,
                                    seleccionada =
                                        categoriaElegida == categoria,
                                    onClick = {
                                        categoriaElegida = categoria
                                    }
                                )
                            }
                        }
                    }

                    item {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            BotonLimpiarFiltros(
                                onClick = {
                                    busqueda = ""
                                    categoriaElegida = "Todos"
                                }
                            )

                            Button(
                                onClick = {
                                    mostrarDescripciones = !mostrarDescripciones
                                }
                            ) {
                                Text(
                                    if (mostrarDescripciones)
                                        "Ocultar descripciones"
                                    else
                                        "Mostrar descripciones"
                                )
                            }
                        }

                    }

                    item {
                        Text(
                            text = "Eventos encontrados: " +
                                    eventosFiltrados.size,
                            color = Color.LightGray,
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        )
                    }


                    // LISTA DE EVENTOS
                    items(eventosFiltrados) { evento ->
                        //Si el evento está destacado, aparecerá con un aspecto distinto
                        if(evento.destacado){
                            EventoDestacado(
                                evento = evento,
                                onClick = {
                                    eventoSeleccionado = evento
                                    pantalla = "detalle"

                                    nombre = ""
                                    correo = ""
                                    inscrito = false},
                                mostrarDescripcion = mostrarDescripciones
                            )
                        }else{
                            EventoCard(
                                evento = evento,
                                mostrarDescripcion = mostrarDescripciones,
                                onClick = {
                                    eventoSeleccionado = evento
                                    pantalla = "detalle"

                                    nombre = ""
                                    correo = ""
                                    inscrito = false
                                }
                            )
                        }
                    }

                    if (eventosFiltrados.isEmpty()) {
                        item {
                            EstadoVacio()
                        }
                    }

                } else {

                    // PANTALLA DE DETALLE
                    item {

                        Column(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            TextButton(
                                onClick = {
                                    pantalla = "lista"
                                }
                            ) {
                                Text("← Volver")
                            }

                            eventoSeleccionado?.let { evento ->

                                Text(
                                    evento.emoji,
                                    fontSize = 60.sp
                                )
                                //Sustituyo el texto del nombre del evento por la cabecera
                                CabeceraSeccion(
                                    titulo = evento.nombre,
                                    subtitulo = evento.subtitulo,
                                )
                                Text(
                                    evento.fecha,
                                    color = morado,
                                    fontSize = 18.sp
                                )

                                Text(
                                    evento.descripcion,
                                    color = Color.LightGray
                                )

                                HorizontalDivider()

                                Text(
                                    "Apúntate al evento",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OutlinedTextField(
                                    value = nombre,
                                    onValueChange = {
                                        nombre = it
                                    },
                                    label = {
                                        Text("Nombre")
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color.White,
                                        focusedLabelColor = morado,
                                        unfocusedLabelColor = Color.LightGray,
                                        focusedBorderColor = morado,
                                        unfocusedBorderColor = Color.Gray
                                    )
                                )

                                OutlinedTextField(
                                    value = correo,
                                    onValueChange = {
                                        correo = it
                                    },
                                    label = {
                                        Text("Correo electrónico")
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color.White,
                                        focusedLabelColor = morado,
                                        unfocusedLabelColor = Color.LightGray,
                                        focusedBorderColor = morado,
                                        unfocusedBorderColor = Color.Gray
                                    )
                                )

                                Button(
                                    onClick = {
                                        scope.launch {
                                            when {
                                                nombre.isBlank() -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce tu nombre"
                                                    )
                                                }

                                                correo.isBlank() -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce tu correo electrónico"
                                                    )
                                                }

                                                !correo.contains("@") -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce un correo electrónico válido"
                                                    )
                                                }

                                                else -> {
                                                    inscrito = true

                                                    snackbarHostState.showSnackbar(
                                                        "¡Inscripción realizada correctamente!"
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Apuntarme")
                                }

                                //Cambia el valor de destacado del evento
                                Button(
                                    onClick = {
                                        evento.destacado = !evento.destacado
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                ){
                                    Text(
                                        "Cambiar valor de destacado"
                                    )
                                }

                                //Muestra este mensaje para comprobar si el evento está destacado o no
                                //Para que el mensaje se actualice, debe volver a la pantalla anterior
                                //y despues entrar otra vez al detalle
                                if (evento.destacado) {
                                    Text(
                                        "Este evento está destacado",
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }else{
                                    Text(
                                        "Este evento NO está destacado",
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                if (inscrito) {
                                    Text(
                                        "¡Inscripción simulada completada!",
                                        color = morado,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
