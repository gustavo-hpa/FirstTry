package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

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
        SmartTimer("titulo", h, m, s,false, isDarkTheme = isDarkTheme)
        SmartCounter("titulo",100, isDarkTheme = isDarkTheme)
        SmartCounter("titulo", clikable = false, isDarkTheme = isDarkTheme)
        Button(onClick = { onScreenSelected(Ecras.Login) }, colors = ButtonDefaults.buttonColors(containerColor = MutedWarmGold)) {
            Text("Ir para Login")
        }
    }
}