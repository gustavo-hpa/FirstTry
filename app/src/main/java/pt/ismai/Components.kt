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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

val BasketballOrange = Color(0xFFC94C24) // Cor da bola (Vinho/Laranja escuro)
val WarmYellow = Color(0xFFFFB300)      // Amarelo quente
val DarkBackgroundStart = Color(0xFF1A1A1A)
val DarkBackgroundEnd = Color(0xFF4E1810) // Vinho escuro
val LightBackgroundStart = Color(0xFFFFF5E6)
val LightBackgroundEnd = Color(0xFFFFCCBC)

@Composable
fun MainContent(
    ecra: Ecras, 
    onScreenSelected: (Ecras) -> Unit, 
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onLocaleChange: (Locale) -> Unit
) {
    val backgroundBrush = if (isDarkTheme) {
        Brush.verticalGradient(listOf(DarkBackgroundStart, DarkBackgroundEnd))
    } else {
        Brush.verticalGradient(listOf(LightBackgroundStart, LightBackgroundEnd))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundBrush),
        contentAlignment = Alignment.Center,
    ) {
        when (ecra) {
            Ecras.Home -> Home(onScreenSelected)
            Ecras.Statistic -> Statistics()
            Ecras.Workout -> Workout()
            Ecras.Setting -> Setting(onScreenSelected, isDarkTheme, onThemeToggle, onLocaleChange)
            Ecras.Login -> Login(onScreenSelected)
            Ecras.Signup -> Signup(onScreenSelected)
            Ecras.Profile -> Profile()
            Ecras.AccountManagement -> AccountManagement()
            Ecras.NotificationsAndSounds -> NotificationsAndSounds()
            Ecras.PrivacyAndSecurity -> PrivacyAndSecurity()
            Ecras.HelpAndAbout -> HelpAndAbout()
        }
    }
}

@Composable
fun Bottombar(
    currentScreen: Ecras,
    onScreenSelected: (Ecras) -> Unit,
    containerColor: Color = Color(0xFF3A86FF),
    contentColor: Color = Color.White,
    indicatorColor: Color = Color(0xFF265DAB)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val items = listOf(
            Triple(Ecras.Home, stringResource(id = R.string.home), R.drawable.home),
            Triple(Ecras.Statistic, stringResource(id = R.string.statistics), R.drawable.statistics),
            Triple(Ecras.Workout, stringResource(id = R.string.workout), R.drawable.workout),
            Triple(Ecras.Setting, stringResource(id = R.string.setting), R.drawable.settings)
        )

        items.forEach { (screen, title, iconId) ->
            IconTextButton(
                icon = painterResource(id = iconId),
                text = title,
                screen = screen,
                currentScreen = currentScreen,
                onClick = onScreenSelected,
                modifier = Modifier.weight(1f),
                selectedColor = indicatorColor,
                unselectedColor = Color.Transparent,
                selectedContentColor = contentColor,
                unselectedContentColor = contentColor.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun Topbar(
    ecraAtual: Ecras,
    onScreenSelected: (Ecras) -> Unit,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black,
    isDarkTheme: Boolean = false,
    onThemeToggle: () -> Unit = {}
) {
    val titulo = when (ecraAtual) {
        Ecras.Home -> stringResource(id = R.string.home)
        Ecras.Statistic -> stringResource(id = R.string.statistics)
        Ecras.Workout -> stringResource(id = R.string.workout)
        Ecras.Setting -> stringResource(id = R.string.settings)
        Ecras.Login -> stringResource(id = R.string.login)
        Ecras.Signup -> stringResource(id = R.string.signup)
        Ecras.Profile -> stringResource(id = R.string.profile)
        Ecras.AccountManagement -> stringResource(id = R.string.settings_account_management)
        Ecras.NotificationsAndSounds -> stringResource(id = R.string.settings_notifications_and_sounds)
        Ecras.PrivacyAndSecurity -> stringResource(id = R.string.settings_privacy_and_security)
        Ecras.HelpAndAbout -> stringResource(id = R.string.settings_help_and_about)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = stringResource(id = R.string.menu),
                tint = contentColor
            )
        }

        TextButton(onClick = {}) {
            Text(
                text = titulo,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = onThemeToggle) {
                Icon(
                    painter = painterResource(id = if (isDarkTheme) R.drawable.sun else R.drawable.moon),
                    contentDescription = stringResource(id = R.string.toggle_theme),
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = { onScreenSelected(Ecras.Profile) }) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = stringResource(id = R.string.profile),
                    tint = contentColor
                )
            }
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
    modifier: Modifier = Modifier,
    selectedColor: Color,
    unselectedColor: Color,
    selectedContentColor: Color,
    unselectedContentColor: Color
) {
    val selected = currentScreen == screen

    val backgroundColor = if (selected) selectedColor else unselectedColor
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onClick(screen) }
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = text,
                color = contentColor,
                fontSize = 12.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                maxLines = 1
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
                        Text(stringResource(id = R.string.minus_1_hour))
                    }
                    TextButton(onClick = { totalSeconds += 3600 }) {
                        Text(stringResource(id = R.string.plus_1_hour))
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { totalSeconds -= 600 },
                        enabled = totalSeconds >= 600
                    ) {
                        Text(stringResource(id = R.string.minus_10_minutes))
                    }
                    TextButton(
                        onClick = { totalSeconds -= 60 },
                        enabled = totalSeconds >= 60
                    ) {
                        Text(stringResource(id = R.string.minus_1_minute))
                    }
                    TextButton(onClick = { totalSeconds += 60 }) { Text(stringResource(id = R.string.plus_1_minute)) }
                    TextButton(onClick = { totalSeconds += 600 }) { Text(stringResource(id = R.string.plus_10_minutes)) }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { totalSeconds -= 10 },
                        enabled = totalSeconds >= 10
                    ) {
                        Text(stringResource(id = R.string.minus_10_seconds))
                    }
                    TextButton(
                        onClick = { totalSeconds-- },
                        enabled = totalSeconds > 0
                    ) {
                        Text(stringResource(id = R.string.minus_1_second))
                    }
                    TextButton(onClick = { totalSeconds++ }) { Text(stringResource(id = R.string.plus_1_second)) }
                    TextButton(onClick = { totalSeconds += 10 }) { Text(stringResource(id = R.string.plus_10_seconds)) }
                }

                TextButton(onClick = { totalSeconds = 0 }, enabled = totalSeconds > 0) {
                    Text(stringResource(id = R.string.reset))
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
                    text = selectedOption.value.ifEmpty { stringResource(id = R.string.select) },
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
                    contentDescription = if (expanded) stringResource(id = R.string.collapse) else stringResource(id = R.string.expand),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = modifierImage.size(24.dp)
                )
            }
        }

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
                    TextButton(
                        onClick = { if (numeroAtual > 0) numeroAtual-- },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = modifierButtons
                    ) {
                        Text(
                            text = stringResource(id = R.string.minus),
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
                    TextButton(
                        onClick = { numeroAtual++ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = modifierButtons
                    ) {
                        Text(
                            text = stringResource(id = R.string.plus),
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

@Composable
fun BasketballTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isDark: Boolean,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BasketballOrange,
            unfocusedBorderColor = if (isDark) Color.Gray else Color.LightGray,
            focusedLabelColor = BasketballOrange,
            cursorColor = BasketballOrange
        )
    )
}

@Composable
fun SocialButton(
    text: String,
    isDark: Boolean,
    icon: ImageVector,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isDark) Color.White else Color.Black
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.login_with, text))
    }
}