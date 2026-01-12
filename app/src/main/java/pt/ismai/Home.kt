package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import pt.ismai.components.CustomDropdown
import pt.ismai.components.SmartCounter
import pt.ismai.components.SmartTimer

@Composable
fun Home(onScreenSelected: (Ecras) -> Unit, isDarkTheme: Boolean) {
    Column() {
        val h = rememberSaveable { mutableStateOf(0) }
        val m = rememberSaveable { mutableStateOf(0) }
        val s = rememberSaveable { mutableStateOf(0) }
        SmartTimer("titulo", h, m, s, isDarkTheme = isDarkTheme)
        val selecionada = rememberSaveable { mutableStateOf("") }
        val opcoes = listOf("Eletrônicos", "Roupas", "Livros", "Casa", "Esportes", "paraleolipicoibeubfaibi")
        CustomDropdown("titulo", opcoes, selecionada, isDarkTheme = isDarkTheme)
        SmartTimer("titulo", h, m, s, false, isDarkTheme = isDarkTheme)
        SmartCounter("titulo", 100, isDarkTheme = isDarkTheme)
        SmartCounter("titulo", clikable = false, isDarkTheme = isDarkTheme)
    }
}