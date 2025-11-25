package pt.ismai

import java.util.Date
import kotlin.time.Duration

enum class Ecras {
    Home,
    Statistic,
    Setting,
    Workout,
    Login,
    Signup
}

enum class MetodoAvalicao {
    POR_TEMPO_MAXIMO, // tempoFeito
    POR_TEMPO_ACERTOS, // acertos"definidos", tempoFeito
    POR_TEMPO_DISTANCIA, // distanciaDefinida, tempoFeito
    POR_TEMPO_MINIMO, // tempoDefinido, tempoFeito
    POR_TENTATIVA, //acertos, tentativas
    POR_PRECISAO, // acertos
    POR_REPETICOES, // series e repetiçoes
    POR_DISTANCIA_MAXIMA, // distanciaFeita
    POR_ACERTOS_TEMPO, // acertos, tempoDefinido
    POR_DISTANCIA_TEMPO, // tempoDefinido, distanciaFeita
}

enum class NivelDificuldade {
    DIFICIO,
    MEDIA,
    FACIL,
}

enum class Categorias {
    CARDIO,
    FISICO,
    PASSE,
    DRIBLE_DOMINIO,
    ARREMESSO
}

class Exercicio(
    val id: Int,
    val nome: String,
    val descricao: String,
    val categoria: Categorias,
    val metodoAvaliacao: MetodoAvalicao,
    val nivelDificuldade: NivelDificuldade,
    val fotoUrl: String? = null,
    val criadoPorUsuario: Boolean = false,
    val dataCriacao: Date = Date(),

    // Campos opcionais para os diferentes métodos de avaliação
    val tempoDefinido: Duration? = null,      // Para POR_TEMPO e POR_ACERTOS_TEMPO
    val tempoFeito: Duration? = null,         // Para POR_TEMPO_MAXIMO, POR_TEMPO, POR_TEMPO_MINIMO, POR_ACERTOS_TEMPO
    val objetivo: Int? = null,           // Para POR_TEMPO_MINIMO
    val acertos: Int? = null,            // Para POR_TENTATIVA, POR_ACERTOS_TEMPO, POR_PRECISAO
    val tentativas: Int? = null,         // Para POR_TENTATIVA
    val series: Int? = null,             // Para POR_REPETICOES
    val repeticoes: Int? = null,         // Para POR_REPETICOES
    val distanciaDefinida: Double? = null, // Para POR_DISTANCIA
    val distanciaFeita: Double? = null     // Para POR_DISTANCIA
)

data class Treino(
    val id: Int,
    val nome: String,
    val descricao: String,
    val categorias: List<Categorias>,
    val nivelDificuldade: NivelDificuldade,
    val duracao: Duration,
    val exercicios: List<Exercicio>,
    val dataCriacao: Date = Date()
)