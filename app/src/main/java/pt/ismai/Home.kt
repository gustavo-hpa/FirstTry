package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun Home() {
    Column() {
        val h = rememberSaveable { mutableStateOf(0) }
        val m = rememberSaveable { mutableStateOf(0) }
        val s = rememberSaveable { mutableStateOf(0) }
        /*SmartTimer("titulo", h, m, s)
        val selecionada = rememberSaveable { mutableStateOf("") }
        val opcoes = listOf("Eletrônicos", "Roupas", "Livros", "Casa", "Esportes", "paraleolipicoibeubfaibi")
        CustomDropdown("titulo", opcoes, selecionada)*/
        SmartTimer("titulo", h, m, s,false)
        SmartCounter("titulo")
        SmartCounter("titulo", clikable = false)

    }
}