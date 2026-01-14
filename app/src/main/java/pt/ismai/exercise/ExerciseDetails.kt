package pt.ismai.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import pt.ismai.Exercicio
import pt.ismai.R
import pt.ismai.components.*
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ExerciseDetails(
    exercicio: Exercicio?,
    isDarkTheme: Boolean,
) {
    if (exercicio == null) return

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dataTexto = dateFormat.format(exercicio.dataCriacao)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Cabeçalho: Apenas texto (casinha removida conforme pedido)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = exercicio.nome,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Card de Ilustração
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isDarkTheme) SurfaceDark else Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.workout),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                colorFilter = ColorFilter.tint(BasketballOrange)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Informações Principais
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(if (isDarkTheme) SurfaceDark.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.4f))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Lado Esquerdo: Intensidade
                Column {
                    Text(
                        text = "Intensidade",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isDarkTheme) Color.Gray else Color.DarkGray
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DifficultyIndicator(nivel = exercicio.nivelDificuldade)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = exercicio.nivelDificuldade.name,
                            fontWeight = FontWeight.Bold,
                            color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                        )
                    }
                }

                // Lado Direito: Categoria (Alterado de Volume para Categoria)
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Categoria",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isDarkTheme) Color.Gray else Color.DarkGray
                    )
                    Text(
                        text = exercicio.categoria.name,
                        fontWeight = FontWeight.Bold,
                        color = BasketballOrange
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Avaliação Detalhada (Campos Opcionais Expostos)
            Text(
                text = "Avaliação e Metas",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) MutedWarmGold else BasketballOrange
            )
            Text(
                text = getAvaliacaoFormatada(exercicio), // Chama a nova função do componente
                style = MaterialTheme.typography.bodyMedium,
                color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Descrição Técnica
            Text(
                text = "Instruções",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) MutedWarmGold else BasketballOrange
            )
            Text(
                text = exercicio.descricao,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp,
                color = if (isDarkTheme) TextPrimaryOnDark.copy(alpha = 0.9f) else Color.Black.copy(alpha = 0.8f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Rodapé com Data de Criação e Foco
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.statistics),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Criado em: $dataTexto",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "Foco: Execução Controlada",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}