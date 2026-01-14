package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.launch
import pt.ismai.data.ExerciseSeeder
import pt.ismai.data.WorkoutSeeder
import pt.ismai.data.DatabaseManager

@Composable
fun Home() {
    Column {

        val scope = rememberCoroutineScope()
        val dbManager = DatabaseManager()

        val isUploading = rememberSaveable { mutableStateOf(false) }

        Button(
            onClick = {
                scope.launch {
                    isUploading.value = true

                    // 1. Popula a lista de exercícios
                    ExerciseSeeder.uploadAllExercises(dbManager)

                    // 2. Popula os treinos pré-configurados
                    WorkoutSeeder.uploadAllWorkouts(dbManager)

                    isUploading.value = false
                }
            },
            enabled = !isUploading.value
        ) {
            androidx.compose.material3.Text(
                if (isUploading.value) "A popular Firebase..." else "Popular Base de Dados"
            )
        }
    }
}