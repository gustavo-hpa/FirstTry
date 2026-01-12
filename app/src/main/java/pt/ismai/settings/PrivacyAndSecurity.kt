package pt.ismai.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pt.ismai.R
import pt.ismai.components.SettingsGroup
import pt.ismai.components.SettingsMenuItem
import pt.ismai.components.SettingsSwitchItem

@Composable
fun PrivacyAndSecurity(isDarkTheme: Boolean) {
    var twoFactorAuthEnabled by remember { mutableStateOf(false) }
    var biometricAuthEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Login e Acesso
        SettingsGroup(stringResource(id = R.string.login_and_access), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.change_password),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsSwitchItem(
                title = stringResource(id = R.string.two_factor_authentication),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = twoFactorAuthEnabled,
                onCheckedChange = { twoFactorAuthEnabled = it }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsSwitchItem(
                title = stringResource(id = R.string.biometric_authentication),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = biometricAuthEnabled,
                onCheckedChange = { biometricAuthEnabled = it }
            )
        }

        // Sessões
        SettingsGroup(stringResource(id = R.string.sessions), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.connected_devices),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Privacidade Social
        SettingsGroup(stringResource(id = R.string.social_privacy), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.profile_visibility),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.blocked_users),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Permissões do Sistema
        SettingsGroup(stringResource(id = R.string.system_permissions), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.manage_permissions),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
