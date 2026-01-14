package pt.ismai.exercise

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pt.ismai.*
import pt.ismai.R
import pt.ismai.components.*
import pt.ismai.data.AuthManager
import pt.ismai.data.DatabaseManager
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun AddExercise(
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit
) {
    val dbManager = remember { DatabaseManager() }
    val authManager = remember { AuthManager() }
    val scope = rememberCoroutineScope()
    val userId = authManager.getCurrentUserId()

    // Estados Básicos
    var nome by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    val categoria = rememberSaveable { mutableStateOf(Categorias.ARREMESSO.name) }
    val dificuldade = rememberSaveable { mutableStateOf(NivelDificuldade.MEDIA.name) }
    val metodo = rememberSaveable { mutableStateOf(MetodoAvalicao.POR_REPETICOES.name) }

    // Estados para Campos Opcionais (Numéricos)
    val series = rememberSaveable { mutableStateOf(3) }
    val repeticoes = rememberSaveable { mutableStateOf(10) }
    val tentativas = rememberSaveable { mutableStateOf(0) }
    val objetivo = rememberSaveable { mutableStateOf(0) }
    var distancia by rememberSaveable { mutableStateOf("") }

    // Estados para Tempo Definido (SmartTimer)
    val horasDef = rememberSaveable { mutableStateOf(0) }
    val minsDef = rememberSaveable { mutableStateOf(1) }
    val segsDef = rememberSaveable { mutableStateOf(0) }

    // Handlers de Ação
    val onCancel = { onScreenSelected(Ecras.Exercise) }
    val onSave = {
        val tempoTotal = horasDef.value.hours + minsDef.value.minutes + segsDef.value.seconds
        val metodoEnum = MetodoAvalicao.valueOf(metodo.value)

        val novoExercicio = Exercicio(
            id = "",
            nome = nome,
            descricao = descricao,
            categoria = Categorias.valueOf(categoria.value),
            metodoAvaliacao = metodoEnum,
            nivelDificuldade = NivelDificuldade.valueOf(dificuldade.value),
            criadoPorUsuario = true,
            // Preenchimento condicional baseado no metodo
            series = if (metodoEnum == MetodoAvalicao.POR_REPETICOES) series.value else null,
            repeticoes = if (metodoEnum == MetodoAvalicao.POR_REPETICOES) repeticoes.value else null,
            tentativas = if (metodoEnum == MetodoAvalicao.POR_TENTATIVA) tentativas.value else null,
            objetivo = if (metodoEnum == MetodoAvalicao.POR_TEMPO_ACERTOS) objetivo.value else null,
            tempoDefinido = if (metodoEnum in listOf(MetodoAvalicao.POR_ACERTOS_TEMPO, MetodoAvalicao.POR_DISTANCIA_TEMPO, MetodoAvalicao.POR_TEMPO_MINIMO)) tempoTotal else null,
            distanciaDefinida = distancia.toDoubleOrNull()
        )

        scope.launch {
            if (userId != null) {
                dbManager.saveUserExercise(userId, novoExercicio)
                onScreenSelected(Ecras.Exercise)
            }
        }
        Unit
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BasketballTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = "Nome do Exercício",
                    icon = painterResource(id = R.drawable.workout),
                    isDark = isDarkTheme
                )
            }

            item {
                BasketballTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = "Descrição (Opcional)",
                    icon = painterResource(id = R.drawable.menu),
                    isDark = isDarkTheme
                )
            }

            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.weight(1f)) {
                        CustomDropdown("Categoria", Categorias.entries.map { it.name }, categoria, isDarkTheme)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        CustomDropdown("Dificuldade", NivelDificuldade.entries.map { it.name }, dificuldade, isDarkTheme)
                    }
                }
            }

            item {
                CustomDropdown("Método de Avaliação", MetodoAvalicao.entries.map { it.name }, metodo, isDarkTheme)
            }

            // SEÇÃO DINÂMICA: Campos específicos por metodo
            item {
                val currentMetodo = MetodoAvalicao.valueOf(metodo.value)

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Caso: Repetições
                    if (currentMetodo == MetodoAvalicao.POR_REPETICOES) {
                        SmartCounter("Séries", series, true, isDarkTheme) // Nota: SmartCounter precisaria aceitar MutableState para atualizar
                        SmartCounter("Repetições", repeticoes, true, isDarkTheme)
                    }

                    // Caso: Tentativas
                    if (currentMetodo == MetodoAvalicao.POR_TENTATIVA) {
                        SmartCounter("Limite de Tentativas", tentativas, true, isDarkTheme)
                    }

                    // Caso: Tempo Definido (Metodos que usam cronómetro regressivo)
                    if (currentMetodo in listOf(MetodoAvalicao.POR_ACERTOS_TEMPO, MetodoAvalicao.POR_DISTANCIA_TEMPO, MetodoAvalicao.POR_TEMPO_MINIMO)) {
                        SmartTimer("Tempo Alvo", horasDef, minsDef, segsDef, true, isDarkTheme)
                    }

                    // Caso: Distância
                    if (currentMetodo == MetodoAvalicao.POR_TEMPO_DISTANCIA) {
                        BasketballTextField(distancia, { distancia = it }, "Distância (metros)", painterResource(id = R.drawable.statistics), isDarkTheme)
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, if (isDarkTheme) Color.Gray else Color.LightGray)
            ) {
                Text("Cancelar", color = if (isDarkTheme) Color.White else Color.Black)
            }

            Button(
                onClick = onSave,
                enabled = nome.isNotEmpty(),
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange)
            ) {
                Text("Salvar Exercício", fontWeight = FontWeight.Bold)
            }
        }
    }
}