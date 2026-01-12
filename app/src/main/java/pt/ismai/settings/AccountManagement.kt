package pt.ismai.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pt.ismai.Ecras
import pt.ismai.R
import pt.ismai.components.SettingsGroup
import pt.ismai.components.SettingsMenuItem
import pt.ismai.data.AuthManager

@Composable
fun AccountManagement(isDarkTheme: Boolean, onScreenSelected: (Ecras) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val authManager = remember { AuthManager() }

    // Estados para controlar o diálogo e erros
    var showDeleteDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                errorMessage = null // Limpa o erro ao fechar
            },
            title = { Text(stringResource(id = R.string.delete_account)) },
            text = {
                Column {
                    Text(stringResource(id = R.string.delete_account_confirmation))

                    // 🔥 MOSTRA O ERRO SE O LOGIN FOR ANTIGO
                    if (errorMessage != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            errorMessage = null // Limpa erros anteriores
                            try {
                                // Tenta apagar. Se o login for antigo, o FirebaseManager lança exceção
                                authManager.deleteAccount()

                                // Se passou daqui, correu bem:
                                showDeleteDialog = false
                                onScreenSelected(Ecras.Login)

                            } catch (e: Exception) {
                                // Captura "Por segurança, faça Logout e Login..."
                                errorMessage = e.message
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(stringResource(id = R.string.delete))
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    errorMessage = null
                }) {
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