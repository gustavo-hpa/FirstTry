package pt.ismai.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pt.ismai.Categorias
import pt.ismai.Exercicio
import pt.ismai.MetodoAvalicao
import pt.ismai.NivelDificuldade
import pt.ismai.Treino
import pt.ismai.User
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds

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

    // ------------ Saves ------------

    suspend fun saveNativeExercise(exercicio: Exercicio) {
        try {
            val exMap = exercicioToMap(exercicio)
            db.collection("exercises_native")
                .document(exercicio.id.toString())
                .set(exMap)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun saveNativeWorkout(treino: Treino) {
        try {
            val workoutMap = mapOf(
                "id" to treino.id,
                "nome" to treino.nome,
                "descricao" to treino.descricao,
                "categorias" to treino.categorias.map { it.name }, // Salva nomes dos enums
                "nivelDificuldade" to treino.nivelDificuldade.name,
                "duracao" to treino.duracao.inWholeNanoseconds, // Salva como Long (nanossegundos)
                "exercicios" to treino.exercicios.map { exercicioToMap(it) }, // Mapeia lista interna
                "dataCriacao" to treino.dataCriacao,
                "criadoPorUsuario" to treino.criadoPorUsuario,
            )

            db.collection("workouts_native")
                .document(treino.id.toString())
                .set(workoutMap)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun saveUserWorkout(userId: String?, treino: Treino) {
        try {
            if (userId == null) {
                return
            }

            val workoutMap = mutableMapOf(
                "nome" to treino.nome,
                "descricao" to treino.descricao,
                "categorias" to treino.categorias.map { it.name },
                "nivelDificuldade" to treino.nivelDificuldade.name,
                "duracao" to treino.duracao.inWholeNanoseconds,
                "exercicios" to treino.exercicios.map { exercicioToMap(it) },
                "dataCriacao" to treino.dataCriacao,
                "criadoPorUsuario" to true
            )

            // .add() faz com que a BD gere o ID automaticamente
            val docRef = db.collection("users")
                .document(userId)
                .collection("my_workouts")
                .add(workoutMap)
                .await()

            // Opcional: Se quiser que o campo 'id' dentro do documento seja igual ao ID gerado pela BD
            // Note que o ID da BD é uma String. Se o seu modelo exigir Int,
            // o ideal seria mudar o modelo para String no futuro.
            docRef.update("id", docRef.id).await()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun saveUserExercise(userId: String, exercicio: Exercicio) {
        try {
            val exMap = exercicioToMap(exercicio).toMutableMap()
            exMap["criadoPorUsuario"] = true

            val docRef = db.collection("users")
                .document(userId)
                .collection("my_exercises")
                .add(exMap)
                .await()

            docRef.update("id", docRef.id).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Função auxiliar para limpar os nomes do Exercício
    private fun exercicioToMap(ex: Exercicio): Map<String, Any?> {
        return mapOf(
            "id" to ex.id,
            "nome" to ex.nome,
            "descricao" to ex.descricao,
            "categoria" to ex.categoria.name,
            "metodoAvaliacao" to ex.metodoAvaliacao.name,
            "nivelDificuldade" to ex.nivelDificuldade.name,
            "fotoUrl" to ex.fotoUrl,
            "criadoPorUsuario" to ex.criadoPorUsuario,
            "dataCriacao" to ex.dataCriacao,
            "tempoDefinido" to ex.tempoDefinido?.inWholeNanoseconds, // Nomes limpos aqui
            "tempoFeito" to ex.tempoFeito?.inWholeNanoseconds,
            "objetivo" to ex.objetivo,
            "acertos" to ex.acertos,
            "tentativas" to ex.tentativas,
            "series" to ex.series,
            "repeticoes" to ex.repeticoes,
            "distanciaDefinida" to ex.distanciaDefinida,
            "distanciaFeita" to ex.distanciaFeita
        )
    }

    // ------------ Gets ------------

    suspend fun getNativeWorkouts(): List<Treino> {
        return try {
            val snapshot = db.collection("workouts_native").get().await()
            snapshot.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null

                // Mapear exercícios da lista
                val exerciciosRaw = data["exercicios"] as? List<Map<String, Any>> ?: emptyList()
                val listaExercicios = exerciciosRaw.map { ex ->
                    Exercicio(
                        id = data["id"] as? String ?: "",
                        nome = ex["nome"] as String,
                        descricao = ex["descricao"] as String,
                        categoria = Categorias.valueOf(ex["categoria"] as String),
                        metodoAvaliacao = MetodoAvalicao.valueOf(ex["metodoAvaliacao"] as String),
                        nivelDificuldade = NivelDificuldade.valueOf(ex["nivelDificuldade"] as String),
                        tempoDefinido = (ex["tempoDefinido"] as? Long)?.nanoseconds, // Lê o nome limpo
                        // ... preencher os outros campos opcionais se necessário ...
                    )
                }

                Treino(
                    id = data["id"] as? String ?: "",
                    nome = data["nome"] as String,
                    descricao = data["descricao"] as String,
                    categorias = (data["categorias"] as List<String>).map { Categorias.valueOf(it) },
                    nivelDificuldade = NivelDificuldade.valueOf(data["nivelDificuldade"] as String),
                    duracao = (data["duracao"] as Long).nanoseconds, // Lê o nome limpo "duracao"
                    exercicios = listaExercicios,
                    dataCriacao = (data["dataCriacao"] as? com.google.firebase.Timestamp)?.toDate() ?: java.util.Date()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getUserWorkouts(userId: String): List<Treino> {
        return try {
            val snapshot = db.collection("users")
                .document(userId)
                .collection("my_workouts")
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null

                // 1. Mapear a lista interna de exercícios
                val exerciciosRaw = data["exercicios"] as? List<Map<String, Any>> ?: emptyList()
                val listaExercicios = exerciciosRaw.map { ex ->
                    Exercicio(
                        id = ex["id"] as? String ?: "",
                        nome = ex["nome"] as? String ?: "",
                        descricao = ex["descricao"] as? String ?: "",
                        categoria = Categorias.valueOf(ex["categoria"] as? String ?: "OUTROS"),
                        metodoAvaliacao = MetodoAvalicao.valueOf(ex["metodoAvaliacao"] as? String ?: "POR_REPETICOES"),
                        nivelDificuldade = NivelDificuldade.valueOf(ex["nivelDificuldade"] as? String ?: "MEDIA"),
                        tempoDefinido = (ex["tempoDefinido"] as? Long)?.nanoseconds,
                        tempoFeito = (ex["tempoFeito"] as? Long)?.nanoseconds,
                        objetivo = (ex["objetivo"] as? Long)?.toInt(),
                        acertos = (ex["acertos"] as? Long)?.toInt(),
                        tentativas = (ex["tentativas"] as? Long)?.toInt(),
                        series = (ex["series"] as? Long)?.toInt(),
                        repeticoes = (ex["repeticoes"] as? Long)?.toInt(),
                        distanciaDefinida = (ex["distanciaDefinida"] as? Number)?.toDouble(),
                        distanciaFeita = (ex["distanciaFeita"] as? Number)?.toDouble()
                    )
                }

                // 2. Mapear o objeto Treino principal
                Treino(
                    id = doc.id, // Usa o ID do documento do Firestore
                    nome = data["nome"] as? String ?: "",
                    descricao = data["descricao"] as? String ?: "",
                    categorias = (data["categorias"] as? List<String>)?.map {
                        Categorias.valueOf(it)
                    } ?: emptyList(),
                    nivelDificuldade = NivelDificuldade.valueOf(data["nivelDificuldade"] as? String ?: "MEDIA"),
                    duracao = (data["duracao"] as? Long ?: 0L).nanoseconds,
                    exercicios = listaExercicios,
                    dataCriacao = (data["dataCriacao"] as? com.google.firebase.Timestamp)?.toDate() ?: java.util.Date(),
                    criadoPorUsuario = data["criadoPorUsuario"] as? Boolean ?: true
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getAllNativeExercises(): List<Exercicio> {
        return try {
            val snapshot = db.collection("exercises_native").get().await()
            snapshot.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null

                // Mapeamento direto e simples como em getNativeWorkouts
                Exercicio(
                    id = doc.id,
                    nome = data["nome"] as? String ?: "",
                    descricao = data["descricao"] as? String ?: "",
                    categoria = Categorias.valueOf(data["categoria"] as? String ?: "ARREMESSO"),
                    metodoAvaliacao = MetodoAvalicao.valueOf(data["metodoAvaliacao"] as? String ?: "POR_REPETICOES"),
                    nivelDificuldade = NivelDificuldade.valueOf(data["nivelDificuldade"] as? String ?: "MEDIA"),
                    fotoUrl = data["fotoUrl"] as? String,
                    criadoPorUsuario = data["criadoPorUsuario"] as? Boolean ?: false,
                    dataCriacao = (data["dataCriacao"] as? com.google.firebase.Timestamp)?.toDate() ?: java.util.Date(),

                    // Campos de tempo (Duration)
                    tempoDefinido = (data["tempoDefinido"] as? Long)?.nanoseconds,
                    tempoFeito = (data["tempoFeito"] as? Long)?.nanoseconds,

                    // Campos numéricos (Conversão de Long para Int/Double)
                    objetivo = (data["objetivo"] as? Long)?.toInt(),
                    acertos = (data["acertos"] as? Long)?.toInt(),
                    tentativas = (data["tentativas"] as? Long)?.toInt(),
                    series = (data["series"] as? Long)?.toInt(),
                    repeticoes = (data["repeticoes"] as? Long)?.toInt(),
                    distanciaDefinida = (data["distanciaDefinida"] as? Number)?.toDouble(),
                    distanciaFeita = (data["distanciaFeita"] as? Number)?.toDouble()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getUserExercises(userId: String): List<Exercicio> {
        return try {
            val snapshot = db.collection("users")
                .document(userId)
                .collection("my_exercises")
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null
                mapToExercicio(doc.id, data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun mapToExercicio(id: String, data: Map<String, Any?>): Exercicio {
        return Exercicio(
            id = id,
            nome = data["nome"] as? String ?: "",
            descricao = data["descricao"] as? String ?: "",
            categoria = Categorias.valueOf(data["categoria"] as? String ?: "ARREMESSO"),
            metodoAvaliacao = MetodoAvalicao.valueOf(data["metodoAvaliacao"] as? String ?: "POR_REPETICOES"),
            nivelDificuldade = NivelDificuldade.valueOf(data["nivelDificuldade"] as? String ?: "MEDIA"),
            fotoUrl = data["fotoUrl"] as? String,
            criadoPorUsuario = data["criadoPorUsuario"] as? Boolean ?: false,
            dataCriacao = (data["dataCriacao"] as? com.google.firebase.Timestamp)?.toDate() ?: java.util.Date(),
            tempoDefinido = (data["tempoDefinido"] as? Long)?.nanoseconds,
            tempoFeito = (data["tempoFeito"] as? Long)?.nanoseconds,
            objetivo = (data["objetivo"] as? Long)?.toInt(),
            acertos = (data["acertos"] as? Long)?.toInt(),
            tentativas = (data["tentativas"] as? Long)?.toInt(),
            series = (data["series"] as? Long)?.toInt(),
            repeticoes = (data["repeticoes"] as? Long)?.toInt(),
            distanciaDefinida = (data["distanciaDefinida"] as? Number)?.toDouble(),
            distanciaFeita = (data["distanciaFeita"] as? Number)?.toDouble()
        )
    }
}