package pt.ismai.workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pt.ismai.Ecras
import pt.ismai.R
import pt.ismai.Treino
import pt.ismai.components.BasketballOrange
import pt.ismai.components.TabButton
import pt.ismai.components.WorkoutCard
import pt.ismai.data.AuthManager
import pt.ismai.data.DatabaseManager

@Composable
fun Workout(
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit,
    onWorkoutSelected: (Treino) -> Unit
) {
    val dbManager = remember { DatabaseManager() }
    val authManager = remember { AuthManager() }
    val userId = authManager.getCurrentUserId()
    var nativeWorkouts by rememberSaveable { mutableStateOf<List<Treino>>(emptyList()) }
    var userWorkouts by rememberSaveable { mutableStateOf<List<Treino>>(emptyList()) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    if (userId == null) {
        return
    }


    // Carregar dados ao iniciar
    LaunchedEffect(Unit) {
        isLoading = true
        nativeWorkouts = dbManager.getNativeWorkouts()
        // Nota: Futuramente substituir pelo ID real do Auth
        userWorkouts = dbManager.getUserWorkouts(userId) ?: emptyList()
        isLoading = false
    }

    val handleNavigation: (Treino) -> Unit = { treino ->
        onWorkoutSelected(treino)
        onScreenSelected(Ecras.WorkoutDetails)
    }

    // Usamos um Box para permitir que o botão fique sobreposto à lista no fundo da tela
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Abas de Seleção CENTRALIZADAS
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center // Centraliza as opções
            ) {
                TabButton("Biblioteca", selectedTabIndex == 0, { selectedTabIndex = 0 }, isDarkTheme)
                Spacer(modifier = Modifier.width(8.dp))
                TabButton("Meus Treinos", selectedTabIndex == 1, { selectedTabIndex = 1 }, isDarkTheme)
            }

            if (!isLoading) {
                val displayList = if (selectedTabIndex == 0) nativeWorkouts else userWorkouts

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp) // Espaço para o botão não tapar o último item
                ) {
                    items(displayList) { treino ->
                        WorkoutCard(treino, isDarkTheme, handleNavigation)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = BasketballOrange)
                }
            }
        }

        // Botão "Criar novo treino" fixo na parte inferior
        Button(
            onClick = { onScreenSelected(Ecras.AddWorkout) }, // Direciona para AddWorkout
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .fillMaxWidth(0.8f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            // Uso de Image em vez de Icon para evitar mistura de bibliotecas
            Image(
                painter = painterResource(id = R.drawable.outline_add_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Criar novo treino",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}