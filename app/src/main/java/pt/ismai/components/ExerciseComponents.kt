package pt.ismai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import pt.ismai.Categorias
import pt.ismai.Exercicio
import pt.ismai.MetodoAvalicao
import pt.ismai.NivelDificuldade
import pt.ismai.R

@Composable
fun ExerciseItem(exercicio: Exercicio, isDarkTheme: Boolean, onClick: () -> Unit) {

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
            painter = painterResource(id = R.drawable.workout),
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

@Composable
fun FilterSelectionDialog(
    isDarkTheme: Boolean,
    currentCategoria: Categorias?,
    currentDificuldade: NivelDificuldade?,
    currentMetodo: MetodoAvalicao?,
    currentOrigem: Boolean?,
    onDismiss: () -> Unit,
    onApply: (Categorias?, NivelDificuldade?, MetodoAvalicao?, Boolean?) -> Unit,
    onClear: () -> Unit
) {
    // Estados temporários para o diálogo
    var tempCat by remember { mutableStateOf(currentCategoria) }
    var tempDif by remember { mutableStateOf(currentDificuldade) }
    var tempMet by remember { mutableStateOf(currentMetodo) }
    var tempOri by remember { mutableStateOf(currentOrigem) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(16.dp),
            color = if (isDarkTheme) BackgroundDark else Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(id = R.string.add_workout_filter_dialog_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    // 1. Filtro de Origem
                    item {
                        FilterSectionTitle(stringResource(id = R.string.add_workout_filter_dialog_origin), isDarkTheme)
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_origin_all), tempOri == null, isDarkTheme) { tempOri = null }
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_origin_native), tempOri == false, isDarkTheme) { tempOri = false }
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_origin_user), tempOri == true, isDarkTheme) { tempOri = true }
                    }

                    // 2. Filtro de Dificuldade
                    item {
                        FilterSectionTitle(stringResource(id = R.string.add_workout_filter_dialog_difficulty), isDarkTheme)
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_others_all), tempDif == null, isDarkTheme) { tempDif = null }
                        NivelDificuldade.entries.forEach {
                            FilterOptionRow(it.name, tempDif == it, isDarkTheme) { tempDif = it }
                        }
                    }

                    // 3. Filtro de Categoria
                    item {
                        FilterSectionTitle(stringResource(id = R.string.add_workout_filter_dialog_category), isDarkTheme)
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_others_all), tempCat == null, isDarkTheme) { tempCat = null }
                        Categorias.entries.forEach {
                            FilterOptionRow(it.name, tempCat == it, isDarkTheme) { tempCat = it }
                        }
                    }

                    // 4. Filtro de Metodo de Avaliação
                    item {
                        FilterSectionTitle(stringResource(id = R.string.add_workout_filter_dialog_method), isDarkTheme)
                        FilterOptionRow(stringResource(id = R.string.add_workout_filter_dialog_others_all), tempMet == null, isDarkTheme) { tempMet = null }
                        MetodoAvalicao.entries.forEach {
                            FilterOptionRow(it.name.replace("_", " "), tempMet == it, isDarkTheme) { tempMet = it }
                        }
                    }
                }

                // Botões de Ação
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            onClear()
                            onDismiss()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                    ) {
                        Text(stringResource(id = R.string.add_workout_filter_dialog_clear_button))
                    }

                    Button(
                        onClick = { onApply(tempCat, tempDif, tempMet, tempOri) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange)
                    ) {
                        Text(stringResource(id = R.string.add_workout_filter_dialog_apply_button))
                    }
                }
            }
        }
    }
}

@Suppress("UNUSED_PARAMETER")
@Composable
fun FilterSectionTitle(title: String, isDarkTheme: Boolean) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = BasketballOrange,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun FilterOptionRow(
    label: String,
    isSelected: Boolean,
    isDarkTheme: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = BasketballOrange)
        )
        Text(
            text = label,
            color = if (isDarkTheme) Color.White else Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}