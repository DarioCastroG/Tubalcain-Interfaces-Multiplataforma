package net.tubalcain.interfacesmultiplataforma.planazoApp.model

data class Evento(
    val nombre: String,
    val categoria: String,
    val fecha: String,
    val emoji: String,
    val descripcion: String,
    //Añado este atributo para que cada favorito vaya por su cuenta
    var favorito: Boolean = false
)
