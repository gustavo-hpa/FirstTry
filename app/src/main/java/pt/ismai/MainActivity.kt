package pt.ismai

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import pt.ismai.ui.theme.FirstTryTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkTheme by rememberSaveable { mutableStateOf(systemDark) }
            var currentLocale by remember { mutableStateOf(getSystemLocale(this)) }

            CompositionLocalProvider(LocalActivity provides this) {
                LocaleWrapper(locale = currentLocale) {
                    FirstTryTheme(darkTheme = isDarkTheme) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            ProgramaPrincipal(
                                modifier = Modifier.padding(innerPadding),
                                isDarkTheme = isDarkTheme,
                                onThemeToggle = { isDarkTheme = !isDarkTheme },
                                onLocaleChange = { newLocale ->
                                    currentLocale = newLocale ?: getSystemLocale(this)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getSystemLocale(activity: Activity): Locale {
    val systemLocale = activity.resources.configuration.locales.get(0) ?: Locale.getDefault()
    return if (systemLocale.language == "pt" || systemLocale.language == "en") {
        systemLocale
    } else {
        Locale.ENGLISH
    }
}

@Composable
fun LocaleWrapper(locale: Locale, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(locale)

    val localizedContext = context.createConfigurationContext(configuration)

    val lifecycleOwner = LocalLifecycleOwner.current
    val savedStateRegistryOwner = LocalSavedStateRegistryOwner.current
    val activityResultRegistryOwner = LocalActivityResultRegistryOwner.current!!
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current!!

    CompositionLocalProvider(
        LocalContext provides localizedContext,
        LocalLifecycleOwner provides lifecycleOwner,
        LocalSavedStateRegistryOwner provides savedStateRegistryOwner,
        LocalActivityResultRegistryOwner provides activityResultRegistryOwner,
        LocalOnBackPressedDispatcherOwner provides onBackPressedDispatcherOwner
    ) {
        content()
    }
}

@Composable
fun ProgramaPrincipal(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onThemeToggle: () -> Unit = {},
    onLocaleChange: (Locale?) -> Unit = {}
) {
    var currentScreen by rememberSaveable { mutableStateOf(Ecras.Loading) }

    LaunchedEffect(Unit) {
        val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        if (user != null) {
            currentScreen = Ecras.Home
        } else {
            currentScreen = Ecras.Login
        }
    }

    val fullScreenScreens = listOf(Ecras.Login, Ecras.Signup, Ecras.Loading)
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

val LocalActivity = staticCompositionLocalOf<Activity?> { null }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstTryTheme {
        ProgramaPrincipal()
    }
}