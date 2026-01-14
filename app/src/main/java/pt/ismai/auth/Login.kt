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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pt.ismai.components.BasketballOrange
import pt.ismai.components.BasketballTextField
import pt.ismai.components.DarkBackgroundEnd
import pt.ismai.components.DarkBackgroundStart
import pt.ismai.Ecras
import pt.ismai.components.LightBackgroundEnd
import pt.ismai.components.LightBackgroundStart
import pt.ismai.R
import pt.ismai.data.AuthManager

@Composable
fun Login(onScreenSelected: (Ecras) -> Unit) {
    val isDark = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()
    val authManager = AuthManager()

    val backgroundBrush = Brush.verticalGradient(
        colors = if (isDark) listOf(DarkBackgroundStart, DarkBackgroundEnd) else listOf(
            LightBackgroundStart,
            LightBackgroundEnd
        )
    )
    val contentColor = if (isDark) Color.White else Color(0xFF4E1810)

    // Estados para os campos e feedback ao utilizador
    val valueEmail = rememberSaveable { mutableStateOf("") }
    val valuePassword = rememberSaveable { mutableStateOf("") }
    var erroLogin by rememberSaveable { mutableStateOf<String?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(backgroundBrush)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.welcome_back), fontSize = 32.sp, fontWeight = FontWeight.Bold, color = contentColor)
            Text(stringResource(R.string.login_to_continue), fontSize = 16.sp, color = contentColor.copy(alpha = 0.7f), modifier = Modifier.padding(bottom = 32.dp))

            // Campo de Email
            BasketballTextField(
                value = valueEmail.value,
                onValueChange = { valueEmail.value = it; erroLogin = null },
                label = stringResource(R.string.email),
                icon = painterResource(R.drawable.email_icon),
                isDark = isDark,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            BasketballTextField(
                value = valuePassword.value,
                onValueChange = { valuePassword.value = it; erroLogin = null },
                label = stringResource(R.string.password),
                icon = painterResource(R.drawable.lock),
                isDark = isDark,
                isPassword = true
            )

            if (erroLogin != null) {
                Text(
                    text = erroLogin!!,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        try {
                            val result = authManager.login(valueEmail.value, valuePassword.value)
                            if (result != null) {
                                onScreenSelected(Ecras.Home)
                            }
                        } catch (e: Exception) {
                            erroLogin = e.localizedMessage ?: "Erro ao iniciar sessão"
                        } finally {
                            isLoading = false
                        }
                    }
                },
                enabled = !isLoading && valueEmail.value.isNotEmpty() && valuePassword.value.isNotEmpty(),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BasketballOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(stringResource(R.string.login_button), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.no_account), color = contentColor.copy(alpha = 0.8f))
                TextButton(onClick = { onScreenSelected(Ecras.EmailVerificationScreen) }) {
                    Text(stringResource(R.string.sign_up_now), color = BasketballOrange, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}