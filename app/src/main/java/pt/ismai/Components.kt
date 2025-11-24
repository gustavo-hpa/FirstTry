package pt.ismai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Ecras {
    Home,
    Statistic,
    Setting,
    Workout
}

@Composable
fun MainContent(ecra: Ecras, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        when (ecra) {
            Ecras.Home -> Text("🏠 Home Screen", fontSize = 24.sp)
            Ecras.Statistic -> Text("📊 Statistics Screen", fontSize = 24.sp)
            Ecras.Workout -> Text("💪 Workout Screen", fontSize = 24.sp)
            Ecras.Setting -> Text("⚙️ Settings Screen", fontSize = 24.sp)
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
            icon = painterResource(id = R.drawable.outline_add_24),
            text = "Home",
            screen = Ecras.Home,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.outline_add_24),
            text = "Statistics",
            screen = Ecras.Statistic,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.outline_add_24),
            text = "Workout",
            screen = Ecras.Workout,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )

        IconTextButton(
            icon = painterResource(id = R.drawable.outline_add_24),
            text = "Setting",
            screen = Ecras.Setting,
            currentScreen = currentScreen,
            onClick = onScreenSelected,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Topbar(ecraAtual: Ecras) {
    // Define o título de acordo com a tela atual
    val titulo = when (ecraAtual) {
        Ecras.Home -> "Home"
        Ecras.Statistic -> "Statistics"
        Ecras.Workout -> "Workout"
        Ecras.Setting -> "Settings"
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
                painter = painterResource(id = R.drawable.outline_add_24),
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

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.outline_add_24),
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
    modifier: Modifier = Modifier
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }

    // Mantém o valor total de segundos com base nos valores iniciais
    var totalSeconds by rememberSaveable {
        mutableStateOf(
            initialHours.value * 3600 +
                    initialMinutes.value * 60 +
                    initialSeconds.value
        )
    }

    // ✅ Atualiza os valores iniciais **somente se houver diferença**
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

    // ✅ Atualiza totalSeconds se os valores iniciais mudarem de fora
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
            modifier = Modifier.padding(vertical = 16.dp).width(290.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = if (isEditing)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Exibição do timer (usa os .value)
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
            )

            if (isEditing) {
                // Controles
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = { if (totalSeconds >= 3600) totalSeconds -= 3600 }) {
                        Text("-1h")
                    }
                    TextButton(onClick = { totalSeconds += 3600 }) {
                        Text("+1h")
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = { if (totalSeconds >= 600) totalSeconds -= 600 }) {
                        Text("-10m")
                    }
                    TextButton(onClick = { if (totalSeconds >= 60) totalSeconds -= 60 }) {
                        Text("-1m")
                    }
                    TextButton(onClick = { totalSeconds += 60 }) { Text("+1m") }
                    TextButton(onClick = { totalSeconds += 600 }) { Text("+10m") }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = { if (totalSeconds >= 10) totalSeconds -= 10 }) {
                        Text("-10s")
                    }
                    TextButton(onClick = { if (totalSeconds > 0) totalSeconds-- }) {
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
