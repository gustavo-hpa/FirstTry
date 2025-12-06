package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PrivacyAndSecurity() {
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
        SettingsGroup("Login e Acesso") {
            SettingsMenuItem(
                title = "Alterar Senha", 
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsSwitchItem(
                title = "Autenticação em Dois Fatores (2FA)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = twoFactorAuthEnabled,
                onCheckedChange = { twoFactorAuthEnabled = it }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsSwitchItem(
                title = "Autenticação Biométrica (FaceID/TouchID)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = biometricAuthEnabled,
                onCheckedChange = { biometricAuthEnabled = it }
            )
        }

        // Sessões
        SettingsGroup("Sessões") {
            SettingsMenuItem(
                title = "Dispositivos Conectados", 
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Privacidade Social
        SettingsGroup("Privacidade Social") {
            SettingsMenuItem(
                title = "Visibilidade do Perfil (Público/Privado)", 
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = "Usuários Bloqueados",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Permissões do Sistema
        SettingsGroup("Permissões do Sistema") {
            SettingsMenuItem(
                title = "Gerir permissões", 
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
