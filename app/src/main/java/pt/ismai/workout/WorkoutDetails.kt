package pt.ismai.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ismai.Ecras
import pt.ismai.Exercicio
import pt.ismai.Treino
import pt.ismai.R
import pt.ismai.components.*

@Composable
fun WorkoutDetails(
    treino: Treino?,
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit,
    onExerciseSelected: (Exercicio) -> Unit
) {
    if (treino == null) return

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = treino.nome,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isDarkTheme) MutedWarmGold else BasketballOrange
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(if (isDarkTheme) SurfaceDark.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.4f))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                DifficultyIndicator(nivel = treino.nivelDificuldade)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Nível ${treino.nivelDificuldade.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${treino.duracao.inWholeMinutes} min",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isDarkTheme) Color.LightGray else Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                treino.categorias.forEach { cat ->
                    CategoryChip(categoria = cat, isDarkTheme = isDarkTheme)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = treino.descricao,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 18.sp,
                color = if (isDarkTheme) TextPrimaryOnDark.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.list_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkTheme) TextPrimaryOnDark else BasketballOrange,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(treino.exercicios) { ex ->
                ExerciseItem(
                    exercise = ex,
                    isDarkTheme = isDarkTheme,
                    onClick = {
                        onExerciseSelected(ex)
                        onScreenSelected(Ecras.ExerciseDetails)
                    }
                )
            }
        }
    }
}