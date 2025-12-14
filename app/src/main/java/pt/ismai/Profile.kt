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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun Profile(isDarkTheme: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ProfileHeader(isDarkTheme)
        PlayerCard(isDarkTheme)
        PerformanceSummary(isDarkTheme)
        SocialConnections(isDarkTheme)
        AccountSettings(isDarkTheme)
    }
}

@Composable
private fun ProfileHeader(isDarkTheme: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.profile), // Replace with user's actual photo
            contentDescription = stringResource(id = R.string.profile_picture),
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Gustavo Sousa", // Replace with dynamic user name
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MutedWarmGold)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.athlete).uppercase(),
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            text = "@gustavo_baller", // Replace with dynamic user handle
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "\"Treino duro, jogo fácil\"", // Replace with dynamic bio
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PlayerCard(isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.player_card), isDarkTheme = isDarkTheme) {
        Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            InfoChip(stringResource(R.string.position), "Armador")
            InfoChip(stringResource(R.string.height), "1.85m")
            InfoChip(stringResource(R.string.weight), "80kg")
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), modifier = Modifier.padding(horizontal = 16.dp))
         Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            InfoChip(stringResource(R.string.jersey_number), "23")
            InfoChip(stringResource(R.string.dominant_hand), "Destro")
        }
    }
}

@Composable
private fun PerformanceSummary(isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.performance_summary), isDarkTheme = isDarkTheme) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem(label = stringResource(id = R.string.workouts_completed), value = "128")
            StatItem(label = stringResource(id = R.string.hours_trained), value = "96")
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), modifier = Modifier.padding(horizontal = 16.dp))
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.favorite_exercise), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
            Text("Arremesso Livre", fontWeight = FontWeight.Bold) // Dynamic data
        }
    }
}

@Composable
private fun SocialConnections(isDarkTheme: Boolean) {
    SettingsGroup(title = stringResource(id = R.string.social), isDarkTheme = isDarkTheme) {
        SettingsMenuItem(title = stringResource(id = R.string.friends, "15"), icon = painterResource(id = R.drawable.outline_add_24), onClick = { /*TODO*/ })
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        SettingsMenuItem(title = stringResource(id = R.string.groups, "3"), icon = painterResource(id = R.drawable.outline_add_24), onClick = { /*TODO*/ })
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
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
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