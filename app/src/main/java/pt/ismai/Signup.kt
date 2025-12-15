package pt.ismai

import android.app.Activity
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun Signup(onScreenSelected: (Ecras) -> Unit) {
    val isDark = isSystemInDarkTheme()
    val backgroundBrush = Brush.verticalGradient(
        colors = if (isDark) listOf(DarkBackgroundStart, DarkBackgroundEnd) else listOf(LightBackgroundStart, LightBackgroundEnd)
    )
    val contentColor = if (isDark) Color.White else Color(0xFF4E1810)
    val valorEmail = rememberSaveable { mutableStateOf("") }
    val valorPassword = rememberSaveable { mutableStateOf("") }
    val valorConfirmPassword = rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val firebaseManager = remember { FirebaseManager() }

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
                            Log.e("Signup", "Google sign-in failed")
                        }
                    }
                } catch (e: ApiException) {
                    Log.w("Signup", "Google sign in failed", e)
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

            Text("Crie sua conta", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = contentColor, modifier = Modifier.padding(bottom = 8.dp))
            Text("Junte-se ao jogo agora mesmo", fontSize = 16.sp, color = contentColor.copy(alpha = 0.7f), modifier = Modifier.padding(bottom = 32.dp))

            BasketballTextField(valorEmail.value, { valorEmail.value = it }, "Email", Icons.Default.Email, isDark, keyboardType = KeyboardType.Email)
            Spacer(modifier = Modifier.height(16.dp))
            BasketballTextField(valorPassword.value, { valorPassword.value = it }, "Password", Icons.Default.Lock, isDark, isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            BasketballTextField(valorConfirmPassword.value, { valorConfirmPassword.value = it }, "Confirmar Password", Icons.Default.Lock, isDark, isPassword = true)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (valorPassword.value == valorConfirmPassword.value) {
                        coroutineScope.launch {
                            val result = firebaseManager.signUp(valorEmail.value, valorPassword.value)
                            if (result != null) {
                                onScreenSelected(Ecras.Home) // Navigate to home
                            } else {
                                // Handle error
                            }
                        }
                    } else {
                        // Show password mismatch error
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("CADASTRAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
                Text("ou cadastre com", modifier = Modifier.padding(horizontal = 8.dp), fontSize = 14.sp, color = contentColor.copy(alpha = 0.6f))
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
            }
            Spacer(modifier = Modifier.height(24.dp))

            SocialButton("Google", isDark, Icons.Default.Person) {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }

            Spacer(modifier = Modifier.height(12.dp))
            SocialButton("GitHub", isDark, Icons.Default.Person) { /* Lógica GitHub Signup */ }
            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
                Text("Já tem uma conta? ", color = contentColor.copy(alpha = 0.8f))
                TextButton(onClick = { onScreenSelected(Ecras.Login) }) {
                    Text("Entrar", color = BasketballOrange, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}