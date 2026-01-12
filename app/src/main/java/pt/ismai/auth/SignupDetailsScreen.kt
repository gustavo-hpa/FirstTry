package pt.ismai.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pt.ismai.components.BasketballTextField
import pt.ismai.components.DarkBackgroundEnd
import pt.ismai.components.DarkBackgroundStart
import pt.ismai.Ecras
import pt.ismai.components.LightBackgroundEnd
import pt.ismai.components.LightBackgroundStart
import pt.ismai.R
import pt.ismai.components.BasketballOrange
import pt.ismai.data.AuthManager

@Composable
fun Signup(onScreenSelected: (Ecras) -> Unit) {
    val isDark = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val authManager = AuthManager()

    // Estados dos campos
    var username by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    // Estados de Foco
    var isUsernameFocused by rememberSaveable { mutableStateOf(false) }
    var isFullNameFocused by rememberSaveable { mutableStateOf(false) }
    var isPasswordFocused by rememberSaveable { mutableStateOf(false) }

    val backgroundBrush = Brush.verticalGradient(
        colors = if (isDark) listOf(DarkBackgroundStart, DarkBackgroundEnd)
        else listOf(LightBackgroundStart, LightBackgroundEnd)
    )
    val contentColor = if (isDark) Color.White else Color(0xFF4E1810)

    // A estrutura Box garante que o fundo cubra todo o ecrã como no Login/EmailVerification
    Box(modifier = Modifier.fillMaxSize().background(backgroundBrush)) {
        Scaffold(
            containerColor = Color.Transparent, // Importante para ver o gradiente por baixo
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Completar Perfil", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = contentColor)
                Text("Define os teus dados de acesso", fontSize = 16.sp, color = contentColor.copy(alpha = 0.7f), modifier = Modifier.padding(bottom = 32.dp))

                // --- Campo Username ---
                BasketballTextField(
                    value = username,
                    onValueChange = { username = it.lowercase() },
                    label = "Username",
                    icon = painterResource(R.drawable.person),
                    isDark = isDark,
                    modifier = Modifier.onFocusChanged { isUsernameFocused = it.isFocused }
                )
                if (isUsernameFocused || username.isNotEmpty()) {
                    ValidationItem("Mínimo 3 caracteres", username.length in 3..20, contentColor)
                    ValidationItem("Apenas minúsculas, números e '_'", authManager.checkUsernameFormat(username), contentColor)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Campo Nome Completo ---
                BasketballTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Nome Completo",
                    icon = painterResource(R.drawable.person),
                    isDark = isDark,
                    modifier = Modifier.onFocusChanged { isFullNameFocused = it.isFocused }
                )
                if (isFullNameFocused || fullName.isNotEmpty()) {
                    ValidationItem("Mínimo 2 nomes", fullName.trim().split("\\s+".toRegex()).size >= 2, contentColor)
                    ValidationItem("Cada nome com 3+ letras (sem números)", authManager.checkFullNameValid(fullName), contentColor)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Campo Password ---
                BasketballTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Nova Senha",
                    icon = painterResource(R.drawable.lock),
                    isDark = isDark,
                    isPassword = true,
                    modifier = Modifier.onFocusChanged { isPasswordFocused = it.isFocused }
                )
                if (isPasswordFocused || password.isNotEmpty()) {
                    ValidationItem("8 a 32 caracteres", password.length in 8..32, contentColor)
                    ValidationItem("Pelo menos uma letra maiúscula", authManager.checkPasswordHasUpper(password), contentColor)
                    ValidationItem("Pelo menos uma letra minúscula", authManager.checkPasswordHasLower(password), contentColor)
                    ValidationItem("Pelo menos um número", authManager.checkPasswordHasNumber(password), contentColor)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Confirmar Password ---
                BasketballTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirmar Senha",
                    icon = painterResource(R.drawable.lock),
                    isDark = isDark,
                    isPassword = true
                )
                if (confirmPassword.isNotEmpty()) {
                    ValidationItem("As senhas coincidem", confirmPassword == password && password.isNotEmpty(), contentColor)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- BOTÃO REGISTAR (Igual ao EmailVerification e Login) ---
                val isFormValid = username.length in 3..20 &&
                        authManager.checkUsernameFormat(username) &&
                        authManager.checkFullNameValid(fullName) &&
                        password.length in 8..32 &&
                        authManager.checkPasswordHasUpper(password) &&
                        authManager.checkPasswordHasLower(password) &&
                        authManager.checkPasswordHasNumber(password) &&
                        password == confirmPassword

                Button(
                    onClick = {
                        isLoading = true
                        scope.launch {
                            try {
                                authManager.completeSignup(password, username, fullName)
                                onScreenSelected(Ecras.Home)
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar(
                                    message = e.localizedMessage ?: "Ocorreu um erro inesperado"
                                )
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    enabled = !isLoading && isFormValid,
                    modifier = Modifier.fillMaxWidth().height(50.dp), // Altura e largura padronizadas
                    colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange), // Cor da bola
                    shape = RoundedCornerShape(12.dp) // Cantos arredondados padronizados
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                    } else {
                        Text("Finalizar Cadastro", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = {
                        scope.launch {
                            try {
                                // Se o utilizador desistir aqui, apagamos a conta Auth
                                authManager.deleteAccount()
                                onScreenSelected(Ecras.Login)
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar("Erro ao cancelar: ${e.localizedMessage}")
                            }
                        }
                    }
                ) {
                    Text("Cancelar e Apagar Conta", color = Color.Red.copy(alpha = 0.7f))
                }
            }
        }
    }
}

@Composable
fun ValidationItem(text: String, isValid: Boolean, contentColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 12.dp, top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = if (isValid) Color(0xFF4CAF50) else Color(0xFFE57373)
        val icon = if (isValid) "✓ " else "○ "

        Text(
            text = icon + text,
            color = color,
            fontSize = 12.sp,
            fontWeight = if (isValid) FontWeight.Bold else FontWeight.Normal
        )
    }
}
