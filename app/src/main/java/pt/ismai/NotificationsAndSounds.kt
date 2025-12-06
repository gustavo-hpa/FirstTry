package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NotificationsAndSounds() {
    var pushNotificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Alertas
        SettingsGroup("Alertas") {
            SettingsSwitchItem(
                title = "Push Notifications",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = pushNotificationsEnabled,
                onCheckedChange = { pushNotificationsEnabled = it }
            )
        }

        // Preferências
        SettingsGroup("Preferências") {
            SettingsMenuItem(
                title = "Sons e Vibração",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
