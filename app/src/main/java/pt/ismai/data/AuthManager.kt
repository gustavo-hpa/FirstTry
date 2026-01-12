package pt.ismai.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await
import pt.ismai.TipoPerfil
import pt.ismai.User
import java.util.UUID

class AuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dbManager = DatabaseManager() // Instância para gerir os dados
    private val tempPassword = UUID.randomUUID().toString()

    suspend fun login(email: String, password: String): AuthResult? {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null && !user.isEmailVerified) {
                auth.signOut()
                throw Exception("Email não verificado! Verifique a sua caixa de entrada.")
            }
            return result
        } catch (e: Exception) {
            throw e
        }
    }

    fun logout(context: Context, webClientId: String) {
        auth.signOut()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso).signOut()
    }

    suspend fun deleteAccount(): Boolean {
        val user = auth.currentUser ?: return false
        val uid = user.uid

        return try {
            dbManager.deleteUserProfile(uid)

            user.delete().await()

            true
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            throw Exception("Por segurança, faça Logout e Login novamente para concluir a exclusão.")
        } catch (e: Exception) {
            throw e
        }
    }

    //----------------------------------------email verification----------------------------------------

    suspend fun startEmailVerification(email: String) {
        try {
            // Cria o user com senha aleatória apenas para disparar o email
            val result = auth.createUserWithEmailAndPassword(email, tempPassword).await()
            result.user?.sendEmailVerification()?.await()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun checkIfEmailVerified(): Boolean {
        val user = auth.currentUser
        user?.reload()?.await()
        return user?.isEmailVerified ?: false
    }

    // O reenvio não deve tentar criar o utilizador de novo
    suspend fun resendVerificationEmail() {
        try {
            val user = auth.currentUser
            user?.sendEmailVerification()?.await()
                ?: throw Exception("Sessão expirada. Recomece o processo.")
        } catch (e: Exception) {
            throw e
        }
    }

    fun validarFormatoEmail(email: String): String? {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()
        return when {
            email.isBlank() -> "O e-mail não pode estar vazio."
            !email.matches(emailRegex) -> "Introduza um formato de e-mail válido (ex: nome@email.com)."
            else -> null
        }
    }

    // Melhore esta função para não lançar exceções fatais
    suspend fun verificarEmailExiste(email: String): Boolean {
        return try {
            val result = auth.fetchSignInMethodsForEmail(email).await()
            result.signInMethods?.isNotEmpty() ?: false
        } catch (e: Exception) {
            // Se der erro de colisão, o e-mail existe
            e is com.google.firebase.auth.FirebaseAuthUserCollisionException
        }
    }

    suspend fun cancelarSignupSeguro(): Boolean {
        val user = auth.currentUser ?: return false
        val uid = user.uid

        return try {
            // 1. Verifica se já existe um perfil no Firestore
            val perfilExistente = dbManager.getUserProfile(uid)

            if (perfilExistente != null) {
                // Se o perfil existe, é uma conta finalizada.
                // Não apagamos, apenas fazemos logout por segurança.
                auth.signOut()
                return false // Indica que a conta NÃO foi apagada por ser finalizada
            }

            // 2. Se não existe perfil, é uma conta temporária. Apaga da Auth.
            user.delete().await()
            true // Indica que a pré-conta foi apagada com sucesso
        } catch (e: Exception) {
            auth.signOut()
            throw e
        }
    }

    //----------------------------------------signup process----------------------------------------
    suspend fun completeSignup(password: String, username: String, fullName: String) {
        val user = auth.currentUser ?: throw Exception("Sessão expirada")

        // 1. Atualiza para a senha real escolhida pelo utilizador
        user.updatePassword(password).await()

        // 2. Cria o perfil no banco de dados
        val novoUsuario = User(
            id = user.uid,
            username = username.lowercase().trim(),
            email = user.email ?: "",
            nomeCompleto = fullName,
            tipoPerfil = TipoPerfil.ATLETA
        )
        dbManager.createUserProfile(novoUsuario)
    }

    fun checkPasswordHasUpper(pass: String) = pass.any { it.isUpperCase() }
    fun checkPasswordHasNumber(pass: String) = pass.any { it.isDigit() }
    fun checkPasswordHasLower(pass: String) = pass.any { it.isLowerCase() }

    // Validação de Username por requisitos
    fun checkUsernameFormat(user: String) = user.matches("^[a-z0-9_]*$".toRegex())

    // Validação de Nome
    fun checkFullNameValid(name: String): Boolean {
        val words = name.trim().split("\\s+".toRegex())
        if (words.size < 2) return false

        // Regex: Apenas letras (incluindo acentuadas), mínimo 3 caracteres
        val onlyLettersRegex = "^[a-zA-ZÀ-ÿ]{3,}\$".toRegex()

        return words.all { it.matches(onlyLettersRegex) }
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid
}