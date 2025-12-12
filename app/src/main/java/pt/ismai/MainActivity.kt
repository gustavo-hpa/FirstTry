package pt.ismai

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.ismai.ui.theme.FirstTryTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkTheme by rememberSaveable { mutableStateOf(systemDark) }

            // Language state management
            var currentLocale by remember { mutableStateOf(getSystemLocale()) }

            LocaleWrapper(locale = currentLocale) {
                FirstTryTheme(darkTheme = isDarkTheme) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        ProgramaPrincipal(
                            modifier = Modifier.padding(innerPadding),
                            isDarkTheme = isDarkTheme,
                            onThemeToggle = { isDarkTheme = !isDarkTheme },
                            onLocaleChange = { newLocale -> currentLocale = newLocale }
                        )
                    }
                }
            }
        }
    }

    private fun getSystemLocale(): Locale {
        return resources.configuration.locales.get(0) ?: Locale.getDefault()
    }
}

@Composable
fun LocaleWrapper(locale: Locale, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(locale)
    
    // Create a new context with the updated configuration
    val localizedContext = context.createConfigurationContext(configuration)
    
    // Provide this new context to the composable tree, which forces a recomposition 
    // of any composable that uses resources, like stringResource()
    CompositionLocalProvider(LocalContext provides localizedContext) {
        content()
    }
}

@Composable
fun ProgramaPrincipal(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onThemeToggle: () -> Unit = {},
    onLocaleChange: (Locale) -> Unit = {}
) {
    var currentScreen by rememberSaveable { mutableStateOf(Ecras.Home) }

    val fullScreenScreens = listOf(Ecras.Login, Ecras.Signup)
    val isFullScreen = currentScreen in fullScreenScreens

    val barColor = if (isDarkTheme) DarkBackgroundStart else Color(0xFFC94C24)
    val contentColor = if (isDarkTheme) Color.White else Color(0xFF4E1810)

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (!isFullScreen) {
            Topbar(
                ecraAtual = currentScreen,
                onScreenSelected = { newScreen -> currentScreen = newScreen },
                containerColor = barColor,
                contentColor = contentColor,
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )
        }

        MainContent(
            ecra = currentScreen,
            onScreenSelected = { newScreen -> currentScreen = newScreen },
            modifier = Modifier.weight(1f),
            isDarkTheme = isDarkTheme,
            onThemeToggle = onThemeToggle,
            onLocaleChange = onLocaleChange
        )

        if (!isFullScreen) {
            Bottombar(
                currentScreen = currentScreen,
                onScreenSelected = { newScreen -> currentScreen = newScreen },
                containerColor = barColor,
                indicatorColor = if(isDarkTheme) BasketballOrange else Color.White,
                contentColor = if(isDarkTheme) Color.White else Color(0xFF4E1810)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstTryTheme {
        ProgramaPrincipal()
    }
}
