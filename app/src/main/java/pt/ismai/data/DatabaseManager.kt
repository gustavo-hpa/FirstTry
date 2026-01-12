package pt.ismai.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pt.ismai.Exercicio
import pt.ismai.Treino
import pt.ismai.User

class DatabaseManager {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    suspend fun createUserProfile(user: User) {
        db.collection("users").document(user.id).set(user).await()
    }

    suspend fun getUserProfile(uid: String?): User? {
        return try {
            if (uid == null) {
                return null
            }
            else {
                val document = db.collection("users").document(uid).get().await()
                document.toObject(User::class.java)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun deleteUserProfile(uid: String) {
        db.collection("users").document(uid).delete().await()
    }

    // Adicione estas funções à sua classe DatabaseManager
    suspend fun saveNativeWorkout(treino: Treino) {
        try {
            // Guarda o treino na coleção "workouts_native" usando o ID como nome do documento
            db.collection("workouts_native")
                .document(treino.id.toString())
                .set(treino)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
            // Opcional: pode lançar a exceção ou retornar um Result para a UI tratar
        }
    }
    suspend fun getNativeWorkouts(): List<Treino> {
        return try {
            val snapshot = db.collection("workouts_native").get().await()
            snapshot.toObjects(Treino::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserWorkouts(userId: String): List<Treino>? {
        return try {
            // Assume-se que os treinos do usuário estão numa sub-coleção ou filtrados por ID
            val snapshot = db.collection("users").document(userId)
                .collection("my_workouts").get().await()
            snapshot.toObjects(Treino::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveNativeExercise(exercicio: Exercicio) {
        try {
            // Usamos o ID numérico como nome do documento para evitar duplicados
            db.collection("exercises_native")
                .document(exercicio.id.toString())
                .set(exercicio)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllNativeExercises(): List<Exercicio> {
        return try {
            val snapshot = db.collection("exercises_native").get().await()
            snapshot.toObjects(Exercicio::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}