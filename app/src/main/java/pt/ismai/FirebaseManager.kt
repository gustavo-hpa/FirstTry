package pt.ismai

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await



class FirebaseManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    suspend fun signUp(email: String, password: String, username: String, nomeCompleto: String? = null): AuthResult? {
        return try {
            // 1. Criar a conta na Autenticação (Email/Password)
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid

            if (uid != null) {
                // 2. Se correu bem, cria o objeto User
                val novoUsuario = User(
                    id = uid, // O ID do documento é o mesmo da Auth
                    username = username,
                    email = email,
                    nomeCompleto = nomeCompleto,
                    tipoPerfil = TipoPerfil.ATLETA // Default
                )

                // 3. Gravar no Firestore na coleção "users"
                createUserProfile(novoUsuario)
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Função auxiliar para gravar no Firestore
    private suspend fun createUserProfile(user: User) {
        try {
            // Caminho: users -> UID
            db.collection("users").document(user.id).set(user).await()
        } catch (e: Exception) {
            e.printStackTrace()
            // Aqui podes decidir se queres apagar a conta de Auth se a criação do perfil falhar
            // para evitar "utilizadores fantasmas" sem dados.
        }
    }

    suspend fun login(email: String, password: String): AuthResult? {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
            null
        }
    }

    fun logout(context: Context, webClientId: String) {
        auth.signOut()
        // Also sign out from Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso).signOut()
    }

    suspend fun deleteAccount() {
        try {
            auth.currentUser?.delete()?.await()
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
        }
    }

    suspend fun googleSignIn(idToken: String): AuthResult? {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential).await()
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
            null
        }
    }

    suspend fun githubSignIn(activity: Activity): AuthResult? {
        val provider = OAuthProvider.newBuilder("github.com")
        // Opcional: Pedir acesso ao email mesmo que seja privado no GitHub
        provider.addCustomParameter("allow_signup", "false")
        val scopes = listOf("user:email")
        provider.setScopes(scopes)

        return try {
            // O Firebase gere a Activity (WebView) automaticamente
            auth.startActivityForSignInWithProvider(activity, provider.build()).await()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}