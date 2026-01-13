package pt.ismai.components

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import pt.ismai.MetodoAvalicao

@Composable
fun ExerciseItem(exercicio: Exercicio, isDarkTheme: Boolean, onClick: () -> Unit) {
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
            .clickable { onClick() } // Adicionado clique
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

// No ExerciseComponents.kt

@Composable
fun getAvaliacaoFormatada(exercicio: Exercicio): String {
    val sb = StringBuilder()

    // Switch case (when) para descrição do método baseado no seu Enum
    val descricaoMetodo = when (exercicio.metodoAvaliacao) {
        MetodoAvalicao.POR_TEMPO_MAXIMO -> "Resistência máxima: o objetivo é aguentar o maior tempo possível na execução."
        MetodoAvalicao.POR_TEMPO_ACERTOS -> "Eficiência temporal: realizar os acertos definidos e registrar o tempo gasto."
        MetodoAvalicao.POR_TEMPO_DISTANCIA -> "Performance cronometrada: percorrer a distância definida e registrar o tempo."
        MetodoAvalicao.POR_TEMPO_MINIMO -> "Agilidade e velocidade: completar a tarefa tentando baixar o tempo de referência."
        MetodoAvalicao.POR_TENTATIVA -> "Consistência técnica: atingir acertos dentro de um limite de tentativas."
        MetodoAvalicao.POR_PRECISAO -> "Foco na qualidade: avaliação baseada na precisão e percentagem de sucesso."
        MetodoAvalicao.POR_REPETICOES -> "Volume clássico: execução baseada em séries e repetições fixas."
        MetodoAvalicao.POR_DISTANCIA_MAXIMA -> "Alcance máximo: o objetivo é percorrer a maior distância possível."
        MetodoAvalicao.POR_ACERTOS_TEMPO -> "Rapidez e precisão: realizar o máximo de acertos num tempo limite fixo."
        MetodoAvalicao.POR_DISTANCIA_TEMPO -> "Resistência e volume: percorrer a maior distância possível no tempo definido."
    }

    sb.append("$descricaoMetodo\n\n")

    // Exposição dos campos opcionais (apenas se preenchidos)
    // Séries e Repetições
    if (exercicio.series != null && exercicio.series > 0) {
        sb.append("Volume: ${exercicio.series} séries")
        exercicio.repeticoes?.let { if (it > 0) sb.append(" x $it reps") }
        sb.append("\n")
    }

    // Tempos (Definido e Feito)
    exercicio.tempoDefinido?.let {
        sb.append("Tempo Limite: ${it.inWholeMinutes}min ${it.inWholeSeconds % 60}s\n")
    }
    exercicio.tempoFeito?.let {
        sb.append("Tempo Registado: ${it.inWholeMinutes}min ${it.inWholeSeconds % 60}s\n")
    }

    // Acertos, Tentativas e Objetivos
    exercicio.acertos?.let { sb.append("Acertos: $it") }
    exercicio.tentativas?.let { if (it > 0) sb.append(" de $it tentativas") }
    if (exercicio.acertos != null || exercicio.tentativas != null) sb.append("\n")

    exercicio.objetivo?.let { sb.append("Meta de Pontos: $it\n") }

    // Distâncias
    exercicio.distanciaDefinida?.let { sb.append("Distância Alvo: ${it}m\n") }
    exercicio.distanciaFeita?.let { sb.append("Distância Percorrida: ${it}m\n") }

    return sb.toString().trim()
}