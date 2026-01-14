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
import kotlinx.coroutines.tasks.await
import pt.ismai.components.BasketballOrange
import pt.ismai.components.Bottombar
import pt.ismai.components.DarkBackgroundStart
import pt.ismai.components.MainContent
import pt.ismai.components.Topbar
import pt.ismai.data.AuthManager
import pt.ismai.data.DatabaseManager
import pt.ismai.ui.theme.FirstTryTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkTheme by rememberSaveable { mutableStateOf(systemDark) }
            var currentLocale by rememberSaveable { mutableStateOf(getSystemLocale(this)) }

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

    // Em vez de criar uma nova Configuration do zero, vou usar a atual do contexto
    val configuration = Configuration(context.resources.configuration).apply {
        setLocale(locale)
        setLayoutDirection(locale)
    }

    // Cria o contexto localizado
    val localizedContext = context.createConfigurationContext(configuration)

    // Preservar os donos de estado para que o Compose continue funcionando corretamente
    val lifecycleOwner = LocalLifecycleOwner.current
    val savedStateRegistryOwner = LocalSavedStateRegistryOwner.current
    val activityResultRegistryOwner = LocalActivityResultRegistryOwner.current
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    CompositionLocalProvider(
        LocalContext provides localizedContext,
        LocalLifecycleOwner provides lifecycleOwner,
        LocalSavedStateRegistryOwner provides savedStateRegistryOwner,
        // Usamos as verificações de null para segurança
        LocalActivityResultRegistryOwner provides (activityResultRegistryOwner ?: return),
        LocalOnBackPressedDispatcherOwner provides (onBackPressedDispatcherOwner ?: return)
    ) {
        content()
    }
}

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProgramaPrincipal(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onThemeToggle: () -> Unit = {},
    onLocaleChange: (Locale?) -> Unit = {}
) {
    var currentScreen by rememberSaveable { mutableStateOf(Ecras.Loading) }
    var selectedWorkout by remember { mutableStateOf<Treino?>(null) }
    var selectedExercise by remember { mutableStateOf<Exercicio?>(null) }

    LaunchedEffect(Unit) {
        val authManager = AuthManager()
        val dbManager = DatabaseManager()
        val firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        if (user != null) {
            try {
                user.reload().await()

                if (user.isEmailVerified) {
                    val perfil = dbManager.getUserProfile(user.uid)

                    currentScreen = if (perfil != null) Ecras.Home else Ecras.SignupDetailsScreen
                } else {
                    authManager.cancelarSignupSeguro()
                    currentScreen = Ecras.Login
                }
            } catch (_: Exception) {
                firebaseAuth.signOut()
                currentScreen = Ecras.Login
            }
        } else {
            currentScreen = Ecras.Login
        }
    }

    val fullScreenScreens = listOf(Ecras.Login, Ecras.SignupDetailsScreen, Ecras.Loading, Ecras.EmailVerificationScreen)
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
            onLocaleChange = onLocaleChange,
            selectedWorkout = selectedWorkout,
            onWorkoutSelected = { selectedWorkout = it },
            selectedExercise = selectedExercise,
            onExerciseSelected = { selectedExercise = it }
        )

        if (!isFullScreen) {
            Bottombar(
                currentScreen = currentScreen,
                onScreenSelected = { newScreen -> currentScreen = newScreen },
                containerColor = barColor,
                indicatorColor = if (isDarkTheme) BasketballOrange else Color.White,
                contentColor = if (isDarkTheme) Color.White else Color(0xFF4E1810)
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