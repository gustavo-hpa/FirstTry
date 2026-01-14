package pt.ismai.auth

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.ismai.Ecras
import pt.ismai.R
import pt.ismai.components.BasketballOrange
import pt.ismai.components.BasketballTextField
import pt.ismai.data.AuthManager

@Composable
fun EmailVerificationScreen(onVerified: (Ecras) -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var erroEmail by rememberSaveable { mutableStateOf<String?>(null) }
    var isEmailSent by rememberSaveable { mutableStateOf(false) }
    var isVerificando by rememberSaveable { mutableStateOf(false) }
    var timerSeconds by rememberSaveable { mutableStateOf(0) }

    val scope = rememberCoroutineScope()
    val isDark = isSystemInDarkTheme()
    val authManager = AuthManager()

    // Polling de verificação
    LaunchedEffect(isEmailSent) {
        if (isEmailSent) {
            while (true) {
                delay(3000)
                if (authManager.checkIfEmailVerified()) {
                    onVerified(Ecras.SignupDetailsScreen)
                    break
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (!isEmailSent) "Validar E-mail" else "E-mail Enviado",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = if(isDark) Color.White else Color(0xFF4E1810)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // O TextField agora fica sempre visível, mas muda o estado 'enabled'
        BasketballTextField(
            value = email,
            onValueChange = { email = it; erroEmail = null },
            label = "Seu e-mail",
            icon = painterResource(R.drawable.email_icon),
            isDark = isDark,
            enabled = !isEmailSent, // Fica inclicável e claro se enviado
            keyboardType = KeyboardType.Email
        )

        if (erroEmail != null) {
            Text(
                text = erroEmail!!,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 4.dp)
            )
        }

        if (!isEmailSent) {
            // Botão Principal de Envio
            Button(
                onClick = {
                    scope.launch {
                        isVerificando = true
                        try {
                            // 1. Validação de Formato (Mantemos para evitar erros de sintaxe)
                            val erroFormato = authManager.validarFormatoEmail(email)
                            if (erroFormato != null) {
                                erroEmail = erroFormato
                                return@launch
                            }

                            // LOGICA REMOVIDA: Não verificamos mais se o e-mail existe aqui
                            // para evitar dar informações desnecessárias e não travar o processo.

                            // 2. Tenta criar a pré-conta e enviar o e-mail
                            authManager.startEmailVerification(email)
                            isEmailSent = true
                            timerSeconds = 60
                        } catch (_: Exception) {
                            isEmailSent = true
                            timerSeconds = 60
                        } finally {
                            isVerificando = false
                        }
                    }
                },
                enabled = !isVerificando && email.isNotEmpty(),
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isVerificando) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Verificar E-mail", fontWeight = FontWeight.Bold)
                }
            }
        } else {
            // Interface de confirmação e suporte
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Verifique a sua caixa de entrada e a pasta de Spam.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = if(isDark) Color.LightGray else Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = BasketballOrange, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(16.dp))

            // BOTÃO ALTERAR E-MAIL: Apaga a conta e permite editar
            TextButton(
                onClick = {
                    scope.launch {
                        try {
                            // Chama a nova função segura do AuthManager
                            val foiApagada = authManager.cancelarSignupSeguro()

                            if (!foiApagada) {
                                // Opcional: Mostrar um aviso de que a conta já existe e não foi removida
                                erroEmail = "Esta conta já está finalizada. Por favor, faça login."
                            }

                            // Em ambos os casos, voltamos ao estado inicial para permitir novo e-mail
                            isEmailSent = false
                            timerSeconds = 0
                        } catch (e: Exception) {
                            erroEmail = "Erro ao processar: ${e.localizedMessage}"
                        }
                    }
                }
            ) {
                Text("Alterar e-mail", color = BasketballOrange) // Cor definida em Components.kt
            }

            TextButton(
                onClick = {
                    scope.launch {
                        try { authManager.resendVerificationEmail() } catch (_: Exception) {}
                        timerSeconds = 60
                    }
                },
                enabled = timerSeconds == 0
            ) {
                Text(if (timerSeconds > 0) "Reenviar em ${timerSeconds}s" else "Não recebi o e-mail")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
            Text(stringResource(R.string.already_have_an_account), color = contentColor.copy(alpha = 0.8f))
            TextButton(onClick = { onVerified(Ecras.Login) }) {
                Text(stringResource(R.string.login), color = BasketballOrange, fontWeight = FontWeight.Bold)
            }
        }
    }
}