package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Home(onScreenSelected: (Ecras) -> Unit) {
    Column() {
        /*val h = rememberSaveable { mutableStateOf(0) }
        val m = rememberSaveable { mutableStateOf(0) }
        val s = rememberSaveable { mutableStateOf(0) }
        SmartTimer("titulo", h, m, s)
        val selecionada = rememberSaveable { mutableStateOf("") }
        val opcoes = listOf("Eletrônicos", "Roupas", "Livros", "Casa", "Esportes", "paraleolipicoibeubfaibi")
        CustomDropdown("titulo", opcoes, selecionada)
        SmartTimer("titulo", h, m, s,false)
        SmartCounter("titulo",100)
        SmartCounter("titulo", clikable = false)*/
        Button(onClick = { onScreenSelected(Ecras.Login) }) {
            Text("Ir para Login")
        }
    }
}