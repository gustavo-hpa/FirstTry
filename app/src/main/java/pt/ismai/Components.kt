package pt.ismai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainContent(ecra: Ecras, onScreenSelected: (Ecras) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center,
    ) {
        when (ecra) {
            Ecras.Home -> Home(onScreenSelected)  //Text("🏠 Home Screen", fontSize = 24.sp)
            Ecras.Statistic -> Statistics()
            Ecras.Workout -> Workout()
            Ecras.Setting -> Setting()
            Ecras.Login -> Login(onScreenSelected)
            Ecras.Signup -> Signup(onScreenSelected)
            Ecras.Profile -> Profile()
        }
    }
}

@Composable
fun Bottombar(
    currentScreen: Ecras,
    onScreenSelected: (Ecras) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3A86FF))
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconTextButton(
            icon = painterResource(id = R.drawable.home),
            text = "Home",
            screen = Ecras.Home,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.statistics),
            text = "Statistics",
            screen = Ecras.Statistic,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.workout),
            text = "Workout",
            screen = Ecras.Workout,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.settings),
            text = "Setting",
            screen = Ecras.Setting,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Topbar(ecraAtual: Ecras, onScreenSelected: (Ecras) -> Unit) {
    // Define o título de acordo com a tela atual
    val titulo = when (ecraAtual) {
        Ecras.Home -> "Home"
        Ecras.Statistic -> "Statistics"
        Ecras.Workout -> "Workout"
        Ecras.Setting -> "Settings"
        Ecras.Login -> "Login"
        Ecras.Signup -> "Signup"
        Ecras.Profile -> "Profile"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Voltar"
            )
        }
        TextButton (onClick = {}) {
            Text(
                text = titulo,
                fontSize = 22.sp,           // fonte maior
                fontWeight = FontWeight.Bold, // negrito
                color = Color.Black          // cor do título
            )
        }

        IconButton(onClick = { onScreenSelected(Ecras.Profile) }) {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Menu"
            )
        }
    }
}

@Composable
fun IconTextButton(
    icon: Painter,
    text: String,
    screen: Ecras,
    currentScreen: Ecras,
    onClick: (Ecras) -> Unit,
    modifier: Modifier = Modifier
) {
    val selected = currentScreen == screen

    val backgroundColor = if (selected) Color(0xFF265DAB) else Color(0xFF3A86FF)
    val textColor = Color.White
    val iconTint = if (selected) Color.White else Color.Black // 👈 muda a cor do ícone

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onClick(screen) }
            .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = iconTint,        // 👈 aplica a cor
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = text,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SmartTimer(
    titulo: String,
    initialHours: MutableState<Int>,
    initialMinutes: MutableState<Int>,
    initialSeconds: MutableState<Int>,
    clikable: Boolean = true,
    modifierInitialCard: Modifier = Modifier,
    modifierTitle: Modifier = Modifier,
    modifierTime: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }

    var totalSeconds by rememberSaveable {
        mutableStateOf(
            initialHours.value * 3600 +
                    initialMinutes.value * 60 +
                    initialSeconds.value
        )
    }

    LaunchedEffect(totalSeconds) {
        val newHours = totalSeconds / 3600
        val newMinutes = (totalSeconds % 3600) / 60
        val newSeconds = totalSeconds % 60

        if (newHours != initialHours.value ||
            newMinutes != initialMinutes.value ||
            newSeconds != initialSeconds.value
        ) {
            initialHours.value = newHours
            initialMinutes.value = newMinutes
            initialSeconds.value = newSeconds
        }
    }

    LaunchedEffect(
        initialHours.value,
        initialMinutes.value,
        initialSeconds.value
    ) {
        val updatedTotal = initialHours.value * 3600 +
                initialMinutes.value * 60 +
                initialSeconds.value
        if (updatedTotal != totalSeconds) {
            totalSeconds = updatedTotal
        }
    }

    Card(
        modifier = if (clikable) {
            modifier.clickable { isEditing = !isEditing }
        } else modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isEditing) 8.dp else 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isEditing)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifierInitialCard
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = if (isEditing)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifierTitle.padding(top = 8.dp, end = 8.dp, start = 8.dp)
            )

            Text(
                text = String.format(
                    "%02d:%02d:%02d",
                    initialHours.value,
                    initialMinutes.value,
                    initialSeconds.value
                ),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = if (isEditing)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = modifierTime.padding(bottom = 8.dp, end = 8.dp, start = 8.dp)
            )

            if (isEditing) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { totalSeconds -= 3600 },
                        enabled = totalSeconds >= 3600
                    ) {
                        Text("-1h")
                    }
                    TextButton(onClick = { totalSeconds += 3600 }) {
                        Text("+1h")
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { totalSeconds -= 600 },
                        enabled = totalSeconds >= 600
                    ) {
                        Text("-10m")
                    }
                    TextButton(
                        onClick = { totalSeconds -= 60 },
                        enabled = totalSeconds >= 60
                    ) {
                        Text("-1m")
                    }
                    TextButton(onClick = { totalSeconds += 60 }) { Text("+1m") }
                    TextButton(onClick = { totalSeconds += 600 }) { Text("+10m") }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { totalSeconds -= 10 },
                        enabled = totalSeconds >= 10
                    ) {
                        Text("-10s")
                    }
                    TextButton(
                        onClick = { totalSeconds-- },
                        enabled = totalSeconds > 0
                    ) {
                        Text("-1s")
                    }
                    TextButton(onClick = { totalSeconds++ }) { Text("+1s") }
                    TextButton(onClick = { totalSeconds += 10 }) { Text("+10s") }
                }

                TextButton(onClick = { totalSeconds = 0 }, enabled = totalSeconds > 0) {
                    Text("Reset")
                }
            }
        }
    }
}

@Composable
fun CustomDropdown(
    titulo: String,
    opcoes: List<String>,
    selectedOption: MutableState<String>,
    modifierColumn: Modifier = Modifier,
    modifierTitle: Modifier = Modifier,
    modifierSurface: Modifier = Modifier,
    modifierRow: Modifier = Modifier,
    modifierSelectText: Modifier = Modifier,
    modifierImage: Modifier = Modifier,
    modifierOptionCard: Modifier = Modifier,
    modifierOptionColumn: Modifier = Modifier,
    modifierOptionSurface: Modifier = Modifier,
    modifierOptionText: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifierColumn.padding(8.dp)) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifierTitle.padding(start = 4.dp)
        )

        // Campo do dropdown
        Surface(
            onClick = { expanded = !expanded },
            modifier = modifierSurface,
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 2.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifierRow
                    .padding(8.dp)
            ) {
                Text(
                    text = selectedOption.value.ifEmpty { "Selecione..." },
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedOption.value.isEmpty()) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    modifier = modifierSelectText.padding(8.dp)
                )

                Image(
                    painter = painterResource(
                        id = if (expanded) R.drawable.outline_add_24 else R.drawable.outline_add_24
                    ),
                    contentDescription = if (expanded) "Recolher" else "Expandir",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = modifierImage.size(24.dp)
                )
            }
        }

        // Menu como parte do fluxo normal do layout
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            DropdownMenuCustom(
                opcoes = opcoes,
                selectedOption = selectedOption,
                onDismiss = { expanded = false },
                modifierCard = modifierOptionCard,
                modifierColumn = modifierOptionColumn,
                modifierSurface = modifierOptionSurface,
                modifierOptionText = modifierOptionText,
            )
        }
    }
}

@Composable
private fun DropdownMenuCustom(
    opcoes: List<String>,
    selectedOption: MutableState<String>,
    onDismiss: () -> Unit,
    modifierCard: Modifier = Modifier,
    modifierColumn: Modifier = Modifier,
    modifierSurface: Modifier = Modifier,
    modifierOptionText: Modifier = Modifier,
) {
    // Altura máxima para 5 itens (aproximadamente 48.dp cada)
    val maxHeight = 5 * 48

    Card(
        modifier = modifierCard
            .heightIn(max = maxHeight.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = modifierColumn
                .width(IntrinsicSize.Max)
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
        ) {
            opcoes.forEach { opcao ->
                Surface(
                    onClick = {
                        selectedOption.value = opcao
                        onDismiss()
                    },
                    modifier = modifierSurface. fillMaxWidth(),
                    color = if (opcao == selectedOption.value) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ) {
                    Text(
                        text = opcao,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (opcao == selectedOption.value) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        modifier = modifierOptionText
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SmartCounter(
    titulo: String,
    numeroAtual: Int = 0,
    clikable: Boolean = true,
    modifierCard: Modifier = Modifier,
    modifierColumn: Modifier = Modifier,
    modifierTitle: Modifier = Modifier,
    modifierRow: Modifier = Modifier,
    modifierNumber: Modifier = Modifier,
    modifierButtons: Modifier = Modifier,
    modifierTextButtons: Modifier = Modifier,
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }
    var numeroAtual by rememberSaveable { mutableStateOf(numeroAtual) }

    Card(
        modifier = if (clikable) {
            modifierCard.clickable { isEditing = !isEditing }
        } else {
            modifierCard
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isEditing) 8.dp else 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isEditing)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifierColumn
                .padding(8.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = if (isEditing)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifierTitle
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifierRow
            ) {
                if (isEditing) {
                    // Botão -
                    TextButton(
                        onClick = { if (numeroAtual > 0) numeroAtual-- },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = modifierButtons
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = modifierTextButtons
                        )
                    }
                }

                Text(
                    text = "$numeroAtual",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isEditing)
                        MaterialTheme.colorScheme.onPrimaryContainer
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifierNumber
                )

                if (isEditing) {
                    // Botão +
                    TextButton(
                        onClick = { numeroAtual++ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = modifierButtons
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = modifierTextButtons
                        )
                    }
                }
            }
        }
    }
}