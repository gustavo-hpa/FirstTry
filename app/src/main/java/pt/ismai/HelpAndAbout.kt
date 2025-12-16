package pt.ismai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun HelpAndAbout(isDarkTheme: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Suporte
        SettingsGroup(stringResource(id = R.string.support), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.faq),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.contact_us),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.report_a_problem),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Legal
        SettingsGroup(stringResource(id = R.string.legal), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.privacy_policy),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.terms_of_service),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.open_source_licenses),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        // Engajamento
        SettingsGroup(stringResource(id = R.string.engagement), isDarkTheme = isDarkTheme) {
            SettingsMenuItem(
                title = stringResource(id = R.string.rate_the_app),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            SettingsMenuItem(
                title = stringResource(id = R.string.social_media),
                icon = painterResource(id = R.drawable.outline_add_24), // Placeholder
                onClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
