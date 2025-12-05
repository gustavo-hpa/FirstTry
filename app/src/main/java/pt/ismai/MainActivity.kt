package pt.ismai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pt.ismai.ui.theme.FirstTryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProgramaPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProgramaPrincipal(modifier: Modifier = Modifier) {
    var currentScreen by rememberSaveable { mutableStateOf(Ecras.Home) }

    val fullScreenScreens = listOf(
        Ecras.Login,
        Ecras.Signup
    )

    val isFullScreen = currentScreen in fullScreenScreens

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (!isFullScreen) {
            Topbar(
                ecraAtual = currentScreen,
                onScreenSelected = { newScreen -> currentScreen = newScreen },
                containerColor = DarkBackgroundEnd, // Fundo Vinho Escuro (igual à Bottombar)
                contentColor = Color.White          // Texto e Ícones brancos para contraste
            )
        }
        MainContent(currentScreen, onScreenSelected = { newScreen -> currentScreen = newScreen }, modifier = Modifier.weight(1f))
        if (!isFullScreen) {
            Bottombar(
                currentScreen = currentScreen,
                onScreenSelected = { newScreen -> currentScreen = newScreen },
                // Configuração do Tema Basquete:
                containerColor = DarkBackgroundEnd,      // Fundo Vinho Escuro
                indicatorColor = BasketballOrange,       // Botão selecionado Laranja
                contentColor = Color.White               // Ícones/Texto Brancos
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstTryTheme {
        ProgramaPrincipal()
    }
}
