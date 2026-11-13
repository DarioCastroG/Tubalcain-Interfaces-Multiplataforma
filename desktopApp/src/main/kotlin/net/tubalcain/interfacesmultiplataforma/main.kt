package net.tubalcain.interfacesmultiplataforma

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.tubalcain.interfacesmultiplataforma.planazoApp.PlanazoApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "InterfacesMultiplataforma",
    ) {
<<<<<<< HEAD
       PlanazoAppTarea()
=======
        PlanazoApp()
>>>>>>> 633144dfda570a09fb900c47e6d4d9eade5800ab
    }
}