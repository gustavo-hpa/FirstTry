package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun NotificationsAndSounds(isDarkTheme: Boolean) {
    var pushNotificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Alertas
        SettingsGroup(stringResource(id = R.string.alerts), isDarkTheme = isDarkTheme) {
            SettingsSwitchItem(
                title = stringResource(id = R.string.push_notifications),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = pushNotificationsEnabled,
                onCheckedChange = { pushNotificationsEnabled = it }
            )
        }

        // Preferências
        SettingsGroup(stringResource(id = R.string.preferences), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.sound_and_vibration),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
