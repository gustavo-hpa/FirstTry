package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountManagement(isDarkTheme: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Perfil Público
        SettingsGroup("Perfil Público", isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = "Editar Perfil (Foto, Nome, Bio)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Dados da Conta
        SettingsGroup("Dados da Conta", isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = "Dados Pessoais (E-mail, Telefone, Nascimento)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = "Contas Vinculadas (Google, Apple, FB)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Financeiro
        SettingsGroup("Financeiro", isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = "Assinatura/Planos (Status, Pagamento, Histórico)",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Zona de Perigo
        SettingsGroup("Zona de Perigo", isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = "Excluir Conta",
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
