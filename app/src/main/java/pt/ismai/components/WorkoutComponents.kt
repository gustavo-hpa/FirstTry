package pt.ismai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ismai.Categorias
import pt.ismai.NivelDificuldade
import pt.ismai.R
import pt.ismai.Treino

@Composable
fun WorkoutCard(
    treino: Treino,
    isDarkTheme: Boolean,
    onSelect: (Treino) -> Unit
) {
    val cardColors = if (isDarkTheme) {
        CardDefaults.cardColors(containerColor = SurfaceDark.copy(alpha = 0.8f))
    } else {
        CardDefaults.cardColors(containerColor = Color.White)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onSelect(treino) },
        shape = RoundedCornerShape(16.dp),
        colors = cardColors,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BasketballOrange.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.workout),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(BasketballOrange)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = treino.nome,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.statistics),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        colorFilter = ColorFilter.tint(if (isDarkTheme) MutedWarmGold else Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${treino.duracao.inWholeMinutes} min • ${treino.exercicios.size} exs",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isDarkTheme) Color.LightGray else Color.Gray
                    )
                }
            }

            DifficultyIndicator(nivel = treino.nivelDificuldade)
        }
    }
}

@Composable
fun CategoryChip(categoria: Categorias, isDarkTheme: Boolean) {
    Surface(
        color = if (isDarkTheme) MutedWarmGold.copy(alpha = 0.2f) else BasketballOrange.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = categoria.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDarkTheme) MutedWarmGold else BasketballOrange
        )
    }
}

@Composable
fun DifficultyIndicator(nivel: NivelDificuldade) {
    val color = when (nivel) {
        NivelDificuldade.FACIL -> Color.Green
        NivelDificuldade.MEDIA -> Color.Yellow
        NivelDificuldade.DIFICIO -> Color.Red
    }
    Box(
        modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit, isDarkTheme: Boolean) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) BasketballOrange else (if (isDarkTheme) Color.Gray else Color.LightGray),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}