package pt.ismai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ismai.components.MutedWarmGold
import pt.ismai.components.SettingsGroup
import pt.ismai.components.SettingsMenuItem
import pt.ismai.data.DatabaseManager
import pt.ismai.data.AuthManager

@Composable
fun Profile(
    isDarkTheme: Boolean
) {
    val databaseManager = DatabaseManager()
    val authManager = AuthManager()
    // Estado para guardar os dados do utilizador
    var userProfile by rememberSaveable { mutableStateOf<User?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(true) }
    val uid = authManager.getCurrentUserId()

    // Carregar dados ao iniciar a tela
    LaunchedEffect(Unit) {
        userProfile = databaseManager.getUserProfile(uid)
        isLoading = false
    }

    if (isLoading) {
        // Mostra um loading simples enquanto carrega
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = if (isDarkTheme) Color.White else Color.Black)
        }
    } else {
        // Se o userProfile for nulo (erro), mostra um perfil vazio ou mensagem
        val user = userProfile ?: User(username = "Utilizador", email = "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Passamos o objeto 'user' para os componentes filhos
            ProfileHeader(user, isDarkTheme)
            PlayerCard(user, isDarkTheme)
            PerformanceSummary(user, isDarkTheme)
            SocialConnections(isDarkTheme)
            AccountSettings(isDarkTheme)
        }
    }
}

@Composable
private fun ProfileHeader(user: User, isDarkTheme: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.profile), // Ainda hardcoded (precisas do Storage para fotos reais)
            contentDescription = stringResource(id = R.string.profile_picture_description),
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Usa o nome completo ou, se for nulo, o username
            Text(
                text = user.nomeCompleto ?: user.username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MutedWarmGold) // Ou Color(0xFFC9A227)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = user.tipoPerfil.name,
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            text = "@${user.username}",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.biografia ?: stringResource(id = R.string.no_bio),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PlayerCard(user: User, isDarkTheme: Boolean) {
    // Nota: Substitui os stringResource pelos teus R.string reais se existirem
    SettingsGroup(stringResource(id = R.string.player_card), isDarkTheme = isDarkTheme) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoChip(stringResource(id = R.string.position), user.posicao ?: "-")
            InfoChip(stringResource(id = R.string.height), if (user.altura != null) "${user.altura}m" else "-")
            InfoChip(stringResource(id = R.string.weight), if (user.peso != null) "${user.peso}kg" else "-")
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        Row(
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoChip(stringResource(id = R.string.jersey_number), user.numeroCamisa?.toString() ?: "-")
            InfoChip(stringResource(id = R.string.dominant_hand), user.maoDominante ?: "-")
        }
    }
}

@Composable
private fun PerformanceSummary(user: User, isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.performance_summary), isDarkTheme = isDarkTheme) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem(label = stringResource(id = R.string.workouts_completed), value = user.totalTreinos.toString())
            StatItem(label = stringResource(id = R.string.hours_trained), value = user.horasTreinadas.toInt().toString())
        }
        // ... restante do código (Exercício favorito pode ser mais complexo de buscar, podes manter hardcoded ou criar lógica futura) ...
    }
}

@Composable
private fun SocialConnections(isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.social), isDarkTheme = isDarkTheme) {
        SettingsMenuItem(
            title = stringResource(id = R.string.friends, "15"),
            icon = painterResource(id = R.drawable.outline_add_24),
            onClick = { /*TODO*/ })
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        SettingsMenuItem(
            title = stringResource(id = R.string.groups, "3"),
            icon = painterResource(id = R.drawable.outline_add_24),
            onClick = { /*TODO*/ })
    }
}

@Composable
private fun AccountSettings(isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.account), isDarkTheme = isDarkTheme) {
        SettingsMenuItem(
            title = stringResource(id = R.string.edit_profile),
            icon = painterResource(id = R.drawable.profile),
            onClick = { /* Navigate to edit profile screen */ }
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        SettingsMenuItem(
            title = stringResource(id = R.string.change_password),
            icon = painterResource(id = R.drawable.outline_add_24), // Placeholder icon
            onClick = { /* Navigate to change password screen */ }
        )
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
    }
}

@Composable
fun InfoChip(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(text = label.uppercase(), fontSize = 10.sp, color = MutedWarmGold, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}