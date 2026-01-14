package pt.ismai.exercise

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
import pt.ismai.Exercicio
import pt.ismai.R
import pt.ismai.components.BasketballOrange
import pt.ismai.components.ExerciseItem
import pt.ismai.components.TabButton
import pt.ismai.data.AuthManager
import pt.ismai.data.DatabaseManager

@Composable
fun Exercise(
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit,
    onExerciseSelected: (Exercicio) -> Unit
) {
    val dbManager = remember { DatabaseManager() }
    val authManager = remember { AuthManager() }
    val userId = authManager.getCurrentUserId()

    var nativeExercises by rememberSaveable { mutableStateOf<List<Exercicio>>(emptyList()) }
    var userExercises by rememberSaveable { mutableStateOf<List<Exercicio>>(emptyList()) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    val onTabBiblioteca = { selectedTabIndex = 0 }
    val onTabMeusExercicios = { selectedTabIndex = 1 }
    val onCreateExercise = { onScreenSelected(Ecras.AddExercise) }

    LaunchedEffect(Unit) {
        if (userId != null) {
            isLoading = true
            nativeExercises = dbManager.getAllNativeExercises()
            // Agora utiliza a função real do DatabaseManager
            userExercises = dbManager.getUserExercises(userId)
            isLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Abas de Seleção
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TabButton("Biblioteca", selectedTabIndex == 0, onTabBiblioteca, isDarkTheme)
                Spacer(modifier = Modifier.width(8.dp))
                TabButton("Meus Exercícios", selectedTabIndex == 1, onTabMeusExercicios, isDarkTheme)
            }

            if (!isLoading) {
                val displayList = if (selectedTabIndex == 0) nativeExercises else userExercises

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(displayList) { exercicio ->
                        // Ação de clique para o item
                        val onSelect = {
                            onExerciseSelected(exercicio)
                            onScreenSelected(Ecras.ExerciseDetails)
                        }
                        ExerciseItem(exercicio, isDarkTheme, onSelect)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = BasketballOrange)
                }
            }
        }

        // Botão "Criar novo exercício"
        Button(
            onClick = onCreateExercise,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .fillMaxWidth(0.8f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.outline_add_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Criar novo exercício",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

