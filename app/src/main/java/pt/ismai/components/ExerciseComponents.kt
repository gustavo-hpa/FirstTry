package pt.ismai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pt.ismai.Exercicio

@Composable
fun ExerciseItem(exercicio: Exercicio, isDarkTheme: Boolean) {
    val reps = exercicio.repeticoes ?: 0
    val series = exercicio.series ?: 0
    val tempo = exercicio.tempoDefinido?.inWholeMinutes ?: 0

    val detalhe = if (reps > 0) {
        "$series x $reps reps"
    } else {
        "$tempo min"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isDarkTheme) SurfaceDark.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.5f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pt.ismai.R.drawable.workout),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            colorFilter = ColorFilter.tint(BasketballOrange)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = exercicio.nome,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Dificuldade do exercício
                DifficultyIndicator(nivel = exercicio.nivelDificuldade)
            }
            Text(
                text = exercicio.descricao,
                style = MaterialTheme.typography.bodySmall,
                color = if (isDarkTheme) Color.LightGray else Color.Gray
            )
        }
    }
}