package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AccountManagement(isDarkTheme: Boolean, onScreenSelected: (Ecras) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val firebaseManager = remember { FirebaseManager() }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(id = R.string.delete_account)) },
            text = { Text(stringResource(id = R.string.delete_account_confirmation)) },
            confirmButton = {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            firebaseManager.deleteAccount()
                            showDeleteDialog = false
                            onScreenSelected(Ecras.Login)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(stringResource(id = R.string.delete))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Perfil Público
        SettingsGroup(stringResource(id = R.string.public_profile), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.edit_profile_photo_name_bio),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Dados da Conta
        SettingsGroup(stringResource(id = R.string.account_data), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.personal_data_email_phone_birth),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.linked_accounts_google_apple_fb),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Financeiro
        SettingsGroup(stringResource(id = R.string.financial), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.subscription_plans),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Zona de Perigo
        SettingsGroup(stringResource(id = R.string.danger_zone), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.delete_account),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { showDeleteDialog = true }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}