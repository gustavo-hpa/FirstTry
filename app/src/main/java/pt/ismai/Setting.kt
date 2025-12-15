package pt.ismai

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun Setting(
    onScreenSelected: (Ecras) -> Unit,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onLocaleChange: (Locale) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var showLanguageDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val firebaseManager = remember { FirebaseManager() }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            onDismiss = { showLanguageDialog = false },
            onLocaleChange = onLocaleChange
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Barra de Busca
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(stringResource(id = R.string.search_settings)) },
            leadingIcon = { 
                Image(
                    painter = painterResource(id = R.drawable.outline_add_24), // Placeholder
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BasketballOrange,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seção 1: Perfil
        SettingsProfileCard(onNavigate = { onScreenSelected(Ecras.AccountManagement) })

        // Seção 2: Preferências do App
        SettingsGroup(title = stringResource(id = R.string.app_preferences), isDarkTheme = isDarkTheme) {
            SettingsSwitchItem(
                title = stringResource(id = R.string.dark_theme),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                checked = isDarkTheme,
                onCheckedChange = { onThemeToggle() }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.language),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { showLanguageDialog = true }
            )
        }

        // Seção 3: Geral
        SettingsGroup(title = stringResource(id = R.string.general), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.settings_notifications_and_sounds),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { onScreenSelected(Ecras.NotificationsAndSounds) }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.settings_privacy_and_security),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { onScreenSelected(Ecras.PrivacyAndSecurity) }
            )
        }

        // Seção 4: Informações e Suporte
        SettingsGroup(title = stringResource(id = R.string.information_and_support), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.settings_help_and_about),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { onScreenSelected(Ecras.HelpAndAbout) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Rodapé
        LogoutAndVersion(onLogout = { 
            firebaseManager.logout(context, context.getString(R.string.default_web_client_id))
            onScreenSelected(Ecras.Login)
        })
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onLocaleChange: (Locale) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.choose_language)) },
        text = {
            Column {
                Text(stringResource(id = R.string.english), modifier = Modifier.clickable { 
                    onLocaleChange(Locale.ENGLISH)
                    onDismiss()
                }.fillMaxWidth().padding(vertical = 12.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(id = R.string.portuguese), modifier = Modifier.clickable { 
                    onLocaleChange(Locale("pt"))
                    onDismiss()
                }.fillMaxWidth().padding(vertical = 12.dp))
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}
