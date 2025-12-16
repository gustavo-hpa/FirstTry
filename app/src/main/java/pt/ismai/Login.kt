package pt.ismai

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun Login(onScreenSelected: (Ecras) -> Unit) {
    val isDark = isSystemInDarkTheme()
    val backgroundBrush = Brush.verticalGradient(
        colors = if (isDark) listOf(DarkBackgroundStart, DarkBackgroundEnd) else listOf(LightBackgroundStart, LightBackgroundEnd)
    )
    val contentColor = if (isDark) Color.White else Color(0xFF4E1810)
    val valorEmail = rememberSaveable { mutableStateOf("") }
    val valorPassword = rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val firebaseManager = remember { FirebaseManager() }
    val activity = LocalActivity.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    coroutineScope.launch {
                        val authResult = firebaseManager.googleSignIn(account.idToken!!)
                        if (authResult != null) {
                            onScreenSelected(Ecras.Home) // Navigate to home on success
                        } else {
                            // Handle Google sign-in failure
                            Log.e("Login", "Google sign-in failed")
                        }
                    }
                } catch (e: ApiException) {
                    Log.w("Login", "Google sign in failed", e)
                }
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(stringResource(R.string.welcome_back), fontSize = 32.sp, fontWeight = FontWeight.Bold, color = contentColor, modifier = Modifier.padding(bottom = 8.dp))
            Text(stringResource(R.string.login_to_continue), fontSize = 16.sp, color = contentColor.copy(alpha = 0.7f), modifier = Modifier.padding(bottom = 32.dp))

            BasketballTextField(valorEmail.value, { valorEmail.value = it }, stringResource(R.string.email), Icons.Default.Email, isDark, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(16.dp))
            BasketballTextField(valorPassword.value, { valorPassword.value = it }, stringResource(R.string.password), Icons.Default.Lock, isDark, isPassword = true)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val result = firebaseManager.login(valorEmail.value, valorPassword.value)
                            if (result != null) {
                                onScreenSelected(Ecras.Home)
                            } else {
                                // ADICIONA ISTO PARA VERES O ERRO
                                Log.e("LoginError", "Login retornou null. Verifique o FirebaseManager.")
                                // Podes adicionar um Toast aqui se quiseres feedback visual
                            }
                        } catch (e: Exception) {
                            Log.e("LoginError", "Exceção ao logar: ${e.message}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.login_button), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
                Text(stringResource(R.string.or_continue_with), modifier = Modifier.padding(horizontal = 8.dp), fontSize = 14.sp, color = contentColor.copy(alpha = 0.6f))
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
            }
            Spacer(modifier = Modifier.height(24.dp))

            SocialButton(stringResource(R.string.google), isDark, Icons.Default.Person) {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }

            Spacer(modifier = Modifier.height(12.dp))
            SocialButton(stringResource(R.string.github), isDark, Icons.Default.Person) {
                // Já não precisamos do 'context' nem do 'findActivity'
                if (activity != null) {
                    coroutineScope.launch {
                        val result = firebaseManager.githubSignIn(activity)
                        if (result != null) {
                            onScreenSelected(Ecras.Home)
                        } else {
                            Log.e("GithubLogin", "Falha ao iniciar sessão")
                        }
                    }
                } else {
                    // Isto agora é quase impossível de acontecer
                    Log.e("GithubLogin", "Activity não encontrada!")
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
                Text(stringResource(R.string.no_account), color = contentColor.copy(alpha = 0.8f))
                TextButton(onClick = { onScreenSelected(Ecras.Signup) }) {
                    Text(stringResource(R.string.sign_up_now), color = BasketballOrange, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
