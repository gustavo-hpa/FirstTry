package pt.ismai.workout

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch
import pt.ismai.*
import pt.ismai.R
import pt.ismai.components.*
import pt.ismai.data.DatabaseManager
import kotlin.time.Duration.Companion.minutes

@Composable
fun AddWorkout(
    isDarkTheme: Boolean,
    onWorkoutSaved: () -> Unit,
    dbManager: DatabaseManager = DatabaseManager(),
    userId: String
) {
    val scope = rememberCoroutineScope()

    // Estados do Formulário
    var nome by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    val nivelSelecionado = remember { mutableStateOf(NivelDificuldade.MEDIA.name) }
    val exerciciosNoTreino = remember { mutableStateListOf<Exercicio>() }

    // Estados para Seleção de Exercícios
    var showExerciseDialog by remember { mutableStateOf(false) }
    var listaExerciciosDisponiveis by remember { mutableStateOf<List<Exercicio>>(emptyList()) }

    // Carregar exercícios existentes ao iniciar
    LaunchedEffect(Unit) {
        listaExerciciosDisponiveis = dbManager.getAllNativeExercises()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.workout), // Ou "Criar Treino"
            style = MaterialTheme.typography.headlineMedium,
            color = if (isDarkTheme) TextPrimaryOnDark else Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                BasketballTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = "Nome do Treino",
                    icon = painterResource(id = R.drawable.workout),
                    isDark = isDarkTheme
                )
            }

            item {
                BasketballTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = "Descrição",
                    icon = painterResource(id = R.drawable.menu),
                    isDark = isDarkTheme
                )
            }

            item {
                CustomDropdown(
                    titulo = "Dificuldade",
                    opcoes = NivelDificuldade.entries.map { it.name },
                    selectedOption = nivelSelecionado,
                    isDarkTheme = isDarkTheme
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Exercícios (${exerciciosNoTreino.size})",
                        color = if (isDarkTheme) TextPrimaryOnDark else Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Button(
                        onClick = { showExerciseDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_add_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Adicionar")
                    }
                }
            }

            // Lista de exercícios já adicionados ao novo treino
            items(exerciciosNoTreino) { ex ->
                ExerciseItem(
                    exercicio = ex,
                    isDarkTheme = isDarkTheme,
                    onClick = { exerciciosNoTreino.remove(ex) }
                )
            }
        }

        // Botão Salvar
        Button(
            onClick = {
                // Criamos o objeto com ID vazio, pois a BD irá gerar o ID real
                val novoTreino = Treino(
                    id = "",
                    nome = nome,
                    descricao = descricao,
                    categorias = exerciciosNoTreino.map { it.categoria }.distinct(),
                    nivelDificuldade = NivelDificuldade.valueOf(nivelSelecionado.value),
                    duracao = 30.minutes,
                    exercicios = exerciciosNoTreino.toList(),
                    criadoPorUsuario = true // Campo adicionado conforme solicitado
                )

                scope.launch {
                    // A função saveUserWorkout agora lida com o ID automático do Firestore
                    dbManager.saveUserWorkout(userId, novoTreino)
                    onWorkoutSaved()
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
            enabled = nome.isNotEmpty() && exerciciosNoTreino.isNotEmpty()
        ) {
            Text("Salvar Treino", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }

    // Dialog para escolher exercícios existentes
    if (showExerciseDialog) {
        ExerciseSelectionDialog(
            exerciciosDisponiveis = listaExerciciosDisponiveis,
            isDarkTheme = isDarkTheme,
            onDismiss = { showExerciseDialog = false },
            onExerciseSelected = { ex ->
                exerciciosNoTreino.add(ex)
                showExerciseDialog = false
            }
        )
    }
}

@Composable
fun ExerciseSelectionDialog(
    exerciciosDisponiveis: List<Exercicio>,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onExerciseSelected: (Exercicio) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
            shape = RoundedCornerShape(16.dp),
            color = if (isDarkTheme) BackgroundDark else Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Selecionar Exercício",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(exerciciosDisponiveis) { ex ->
                        ExerciseItem(
                            exercicio = ex,
                            isDarkTheme = isDarkTheme,
                            onClick = { onExerciseSelected(ex) }
                        )
                    }
                }
            }
        }
    }
}