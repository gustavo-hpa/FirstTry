package pt.ismai.workout

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import pt.ismai.data.AuthManager
import pt.ismai.data.DatabaseManager
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun AddWorkout(
    isDarkTheme: Boolean,
    onScreenSelected: (Ecras) -> Unit,
) {
    val dbManager = DatabaseManager()
    val authManager = AuthManager()
    val userId = authManager.getCurrentUserId()
    val scope = rememberCoroutineScope()

    // Estados do Formulário
    var nome by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    val nivelSelecionado = rememberSaveable { mutableStateOf(NivelDificuldade.MEDIA.name) }
    val categoriaSelecionada = rememberSaveable { mutableStateOf(Categorias.ARREMESSO.name) }

    // --- NOVOS ESTADOS PARA FILTROS ---
    var showFilterDialog by rememberSaveable { mutableStateOf(false) }
    var filterCategoria by rememberSaveable { mutableStateOf<Categorias?>(null) }
    var filterDificuldade by rememberSaveable { mutableStateOf<NivelDificuldade?>(null) }
    var filterMetodo by rememberSaveable { mutableStateOf<MetodoAvalicao?>(null) }
    var filterOrigem by rememberSaveable { mutableStateOf<Boolean?>(null) } // null = Todos, true = Usuário, false = Nativo

    // Estados para o SmartTimer (Duração)
    val horas = rememberSaveable { mutableStateOf(0) }
    val minutos = rememberSaveable { mutableStateOf(30) }
    val segundos = rememberSaveable { mutableStateOf(0) }

    // Estados para Exercícios
    val exerciciosNoTreino = rememberSaveable { mutableStateListOf<Exercicio>() }
    var showExerciseDialog by rememberSaveable { mutableStateOf(false) }
    var listaExerciciosDisponiveis by rememberSaveable { mutableStateOf<List<Exercicio>>(emptyList()) }

    // Carregar exercícios existentes ao iniciar
    LaunchedEffect(Unit) {
        listaExerciciosDisponiveis = dbManager.getAllNativeExercises()
        if (userId != null)
            listaExerciciosDisponiveis += dbManager.getUserExercises(userId)
    }

    // Esta lista será passada para o ExerciseSelectionDialog
    val exerciciosFiltrados = remember(listaExerciciosDisponiveis, filterCategoria, filterDificuldade, filterMetodo, filterOrigem) {
        listaExerciciosDisponiveis.filter { ex ->
            (filterCategoria == null || ex.categoria == filterCategoria) &&
                    (filterDificuldade == null || ex.nivelDificuldade == filterDificuldade) &&
                    (filterMetodo == null || ex.metodoAvaliacao == filterMetodo) &&
                    (filterOrigem == null || ex.criadoPorUsuario == filterOrigem)
        }
    }

    val activeFilterColor = if (listaExerciciosDisponiveis != exerciciosFiltrados)
        Color.Red else BasketballOrange

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Nome do Treino
            item {
                BasketballTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = stringResource(id = R.string.add_workout_name_label),
                    icon = painterResource(id = R.drawable.workout),
                    isDark = isDarkTheme
                )
            }

            item {
                BasketballTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = stringResource(id = R.string.add_workout_description_label),
                    icon = painterResource(id = R.drawable.menu),
                    isDark = isDarkTheme
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CustomDropdown(
                        title = stringResource(id = R.string.add_workout_main_category_dropdown),
                        options = Categorias.entries.map { it.name },
                        selectedOption = categoriaSelecionada,
                        isDarkTheme = isDarkTheme
                    )
                    CustomDropdown(
                        title = stringResource(id = R.string.add_workout_difficulty_dropdown),
                        options = NivelDificuldade.entries.map { it.name },
                        selectedOption = nivelSelecionado,
                        isDarkTheme = isDarkTheme
                    )
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text(
                        text = stringResource(id = R.string.add_workout_estimated_duration),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isDarkTheme) TextPrimaryOnDark.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SmartTimer(
                        title = stringResource(id = R.string.add_workout_total_time),
                        initialHours = horas,
                        initialMinutes = minutos,
                        initialSeconds = segundos,
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        modifierInitialCard = Modifier.fillMaxWidth(),
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.add_workout_exercises_header, exerciciosNoTreino.size),
                        color = if (isDarkTheme) TextPrimaryOnDark else Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(
                            onClick = { showFilterDialog = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = if (isDarkTheme) Color.DarkGray else Color.LightGray.copy(alpha = 0.5f)
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.filter_list),
                                contentDescription = stringResource(id = R.string.add_workout_filter_button),
                                colorFilter = ColorFilter.tint(activeFilterColor),
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Button(
                            onClick = { showExerciseDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_add_24),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(stringResource(id = R.string.add_workout_add_button))
                        }
                    }
                }
            }

            items(exerciciosNoTreino) { ex ->
                ExerciseItem(
                    exercise = ex,
                    isDarkTheme = isDarkTheme,
                    onClick = { exerciciosNoTreino.remove(ex) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { onScreenSelected(Ecras.Workout) },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, if (isDarkTheme) Color.Gray else Color.LightGray)
            ) {
                Text(
                    stringResource(id = R.string.cancel),
                    color = if (isDarkTheme) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = {
                    val duracaoTotal = horas.value.hours + minutos.value.minutes + segundos.value.seconds

                    val novoTreino = Treino(
                        id = "",
                        nome = nome,
                        descricao = descricao,
                        categorias = (exerciciosNoTreino.map { it.categoria } + Categorias.valueOf(categoriaSelecionada.value)).distinct(),
                        nivelDificuldade = NivelDificuldade.valueOf(nivelSelecionado.value),
                        duracao = duracaoTotal,
                        exercicios = exerciciosNoTreino.toList(),
                        criadoPorUsuario = true
                    )

                    scope.launch {
                        if (userId != null) {
                            dbManager.saveUserWorkout(userId, novoTreino)
                            onScreenSelected(Ecras.Workout)
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                enabled = nome.isNotEmpty() && exerciciosNoTreino.isNotEmpty()
            ) {
                Text(stringResource(id = R.string.add_workout_save_button), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }

    // Dialog para escolher exercícios
    if (showExerciseDialog) {
        ExerciseSelectionDialog(
            exerciciosDisponiveis = exerciciosFiltrados,
            isDarkTheme = isDarkTheme,
            onDismiss = { showExerciseDialog = false },
            onExerciseSelected = { ex ->
                exerciciosNoTreino.add(ex)
                showExerciseDialog = false
            }
        )
    }
    if (showFilterDialog) {
        FilterSelectionDialog(
            isDarkTheme = isDarkTheme,
            currentCategoria = filterCategoria,
            currentDificuldade = filterDificuldade,
            currentMetodo = filterMetodo,
            currentOrigem = filterOrigem,
            onDismiss = { showFilterDialog = false },
            onApply = { cat, dif, met, ori ->
                filterCategoria = cat
                filterDificuldade = dif
                filterMetodo = met
                filterOrigem = ori
                showFilterDialog = false
            },
            onClear = {
                filterCategoria = null
                filterDificuldade = null
                filterMetodo = null
                filterOrigem = null
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
                    stringResource(id = R.string.add_workout_exercise_selection_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isDarkTheme) TextPrimaryOnDark else Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(exerciciosDisponiveis) { ex ->
                        ExerciseItem(
                            exercise = ex,
                            isDarkTheme = isDarkTheme,
                            onClick = { onExerciseSelected(ex) }
                        )
                    }
                }
            }
        }
    }
}
