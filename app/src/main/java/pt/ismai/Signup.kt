package pt.ismai

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Signup(onScreenSelected: (Ecras) -> Unit) {
    // Detecta se está em modo escuro ou claro (Mesma lógica do Login)
    val isDark = isSystemInDarkTheme()

    // Gradiente de Fundo
    val backgroundBrush = Brush.verticalGradient(
        colors = if (isDark) {
            listOf(DarkBackgroundStart, DarkBackgroundEnd)
        } else {
            listOf(LightBackgroundStart, LightBackgroundEnd)
        }
    )

    val contentColor = if (isDark) Color.White else Color(0xFF4E1810)

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

            // 1. Título e Subtítulo
            Text(
                text = "Crie sua conta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Junte-se ao jogo agora mesmo",
                fontSize = 16.sp,
                color = contentColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 2. Campos de Texto

            // Email
            val valorEmail = rememberSaveable { mutableStateOf("") }
            BasketballTextField(
                value = valorEmail.value,
                onValueChange = { valorEmail.value = it },
                label = "Email",
                icon = Icons.Default.Email,
                isDark = isDark,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Senha
            val valorPassword = rememberSaveable { mutableStateOf("") }
            BasketballTextField(
                value = valorPassword.value,
                onValueChange = { valorPassword.value = it },
                label = "Password",
                icon = Icons.Default.Lock,
                isDark = isDark,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirmar Senha (Novo campo)
            val valorConfirmPassword = rememberSaveable { mutableStateOf("") }
            BasketballTextField(
                value = valorConfirmPassword.value,
                onValueChange = { valorConfirmPassword.value = it },
                label = "Confirmar Password",
                icon = Icons.Default.Lock, // Pode usar Icons.Default.Check se preferir
                isDark = isDark,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Botão de Cadastro Principal
            Button(
                onClick = { /* Lógica de Cadastro Firebase Email */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BasketballOrange
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("CADASTRAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Divisor "Ou" (Mencionei isso para manter o padrão se quiser cadastro social também)
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
                Text(
                    text = "ou cadastre com",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp,
                    color = contentColor.copy(alpha = 0.6f)
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = contentColor.copy(alpha = 0.3f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Botões Sociais (Reutilizados para cadastro rápido)
            SocialButton(
                text = "Google",
                isDark = isDark,
                icon = Icons.Default.Person
            ) { /* Lógica Google Signup */ }

            Spacer(modifier = Modifier.height(12.dp))

            SocialButton(
                text = "GitHub",
                isDark = isDark,
                icon = Icons.Default.Person
            ) { /* Lógica GitHub Signup */ }

            Spacer(modifier = Modifier.weight(1f))

            // 5. Rodapé: Voltar para Login
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(
                    "Já tem uma conta? ",
                    color = contentColor.copy(alpha = 0.8f)
                )
                TextButton(onClick = { onScreenSelected(Ecras.Login) }) {
                    Text(
                        "Entrar",
                        color = BasketballOrange,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}