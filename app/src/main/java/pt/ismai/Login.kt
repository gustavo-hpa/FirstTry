package pt.ismai

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun Login(onScreenSelected: (Ecras) -> Unit) {
    Column {
        Text("⚙️ Email", fontSize = 24.sp)
        val valorDoCampo = rememberSaveable { mutableStateOf("") }
        TextField(
            value = valorDoCampo.value,
            onValueChange = { valorDoCampo.value = it },
            label = { Text( "Email" )})
        Text("⚙️ Password", fontSize = 24.sp)
        val valorPassword = rememberSaveable { mutableStateOf("") }
        TextField(
            value = valorPassword.value,
            onValueChange = { valorPassword.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        TextButton(onClick = {onScreenSelected(Ecras.Signup)}) { Text("sign up") }
    }
}