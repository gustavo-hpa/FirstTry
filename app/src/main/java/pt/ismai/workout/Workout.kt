package pt.ismai.workout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ismai.Ecras
import pt.ismai.R
import pt.ismai.Treino
import pt.ismai.components.BasketballOrange
import pt.ismai.components.MutedWarmGold
import pt.ismai.components.TabButton
import pt.ismai.components.WorkoutCard // Importado o card correto
import pt.ismai.data.DatabaseManager

@Composable
fun Workout(
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit,
    onWorkoutSelected: (Treino) -> Unit // Recebe o callback
) {
    val dbManager = remember { DatabaseManager() }
    var nativeWorkouts by rememberSaveable { mutableStateOf<List<Treino>>(emptyList()) }
    var userWorkouts by rememberSaveable { mutableStateOf<List<Treino>>(emptyList()) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    // Carregar dados ao iniciar
    LaunchedEffect(Unit) {
        isLoading = true
        nativeWorkouts = dbManager.getNativeWorkouts()
        // Nota: Substituir "user_id_here" pelo ID real vindo do Auth futuramente
        userWorkouts = dbManager.getUserWorkouts("user_id_here") ?: emptyList()
        isLoading = false
    }

    val handleNavigation: (Treino) -> Unit = { treino ->
        onWorkoutSelected(treino)
        onScreenSelected(Ecras.WorkoutDetails)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Abas de Seleção
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
            TabButton("Biblioteca", selectedTabIndex == 0, { selectedTabIndex = 0 }, isDarkTheme)
            TabButton("Meus Treinos", selectedTabIndex == 1, { selectedTabIndex = 1 }, isDarkTheme)
        }

        if (!isLoading) {
            val displayList = if (selectedTabIndex == 0) nativeWorkouts else userWorkouts

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(displayList) { treino ->
                    // CHAMADA APENAS COM PARÂMETROS, SEM CORPO { }
                    WorkoutCard(treino, isDarkTheme, handleNavigation)
                }
            }
        }
    }
}