package pt.ismai

import java.util.Date
import kotlin.time.Duration

data class Exercicio(
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

// User,"Armazena dados do usuário, autenticação e preferências."
data class User(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val nomeCompleto: String? = null,
    val tipoPerfil: TipoPerfil = TipoPerfil.ATLETA,
    val dataRegistro: Date = Date(),

    // --- Novos Campos para o Perfil ---
    val biografia: String? = null,
    val posicao: String? = null,      // Ex: "Armador"
    val altura: Double? = null,       // Ex: 1.85
    val peso: Double? = null,         // Ex: 80.0
    val numeroCamisa: Int? = null,    // Ex: 23
    val maoDominante: String? = null, // Ex: "Destro"
    val dataNascimento: Date? = null, // Ex: 1990-05-15

    // --- Estatísticas Simples (Opcional: podes calcular isto depois noutra coleção) ---
    val totalTreinos: Int = 0,
    val horasTreinadas: Double = 0.0
)

// Conexao,Gerencia a lista de amigos do usuário.
data class Conexao(
    val userId: Int,
    val amigoId: Int,
    val dataConexao: Date = Date()
)

// HistoricoTreino,Guarda a instância de um treino que foi realizado.
data class HistoricoTreino(
    val id: Int,
    val userId: Int,
    val treinoId: Int,
    val dataExecucao: Date = Date(),
    val duracaoReal: Duration,
    val historicoExercicios: List<HistoricoExercicio>
)

// HistoricoExercicio,Guarda os resultados brutos de um exercício específico realizado.
data class HistoricoExercicio(
    val id: Int,
    val exercicioId: Int,
    val seriesFeitas: Int,
    val acertosFeitos: Int? = null,
    val tentativasFeitas: Int? = null,
    val tempoRealizado: Duration? = null,
    val observacoes: String? = null
)

// DadosSmartwatch,Armazena dados biométricos coletados durante o treino (ligado ao HistoricoTreino).
data class DadosSmartwatch(
    val historicoTreinoId: Int,
    val bpmMedio: Int,
    val calorias: Double,
    val tempoZonaEsforco: Duration? = null  )

// Quadra,Define uma quadra de basquete no mapa.
data class Quadra(
    val id: Int,
    val nome: String,
    val latitude: Double,
    val longitude: Double,
    val iluminacao: Boolean,
    val rede: Boolean,
    val piso: QualidadePiso,
    val adicionadaPorAdmin: Boolean = false
)

// Checkin,"Registra quando um usuário está na quadra (para a funcionalidade ""Quem está na quadra?"")."
data class Checkin(
    val id: Int,
    val userId: Int,
    val quadraId: Int,
    val dataCheckin: Date = Date(),
    val ativoAte: Date // TTL de algumas horas
)

// RachaEvento,Objeto principal para organizar jogos.
data class RachaEvento(
    val id: Int,
    val nome: String,
    val quadraId: Int,
    val organizadorId: Int,
    val dataHora: Date,
    val publico: Boolean,
    val participantes: List<Int> // Lista de IDs de usuários
)

// Grupo,Define um grupo de chat.
data class Grupo(
    val id: Int,
    val nome: String,
    val membros: List<Int>,
    val administradorId: Int
)

// Mensagem,Objeto genérico para mensagens de chat (individual ou grupo).
data class Mensagem(
    val id: Int,
    val remetenteId: Int,
    val chatId: Int, // ID da conversa privada ou do Grupo
    val tipo: TipoMensagem,
    val conteudo: String,
    val dataEnvio: Date = Date(),
    val dataExpiracao: Date // 7 dias depois
)

// Noticia,Armazena dados do feed de notícias.
data class Noticia(
    val id: Int,
    val titulo: String,
    val urlFonte: String,
    val dataPublicacao: Date = Date(),
    val snippet: String? = null
)

//----------------------------------------------------------------------------------------------------------------------------------
// ENUMS

enum class Ecras {
    Home,
    Statistic,
    Workout,
    Setting, // This will be the main settings screen
    Login,
    SignupDetailsScreen,
    Profile,
    // New settings screens
    AccountManagement,
    NotificationsAndSounds,
    PrivacyAndSecurity,
    HelpAndAbout,
    Loading,
    EmailVerificationScreen,
    WorkoutDetails,
    ExerciseDetails
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

enum class TipoMensagem {
    TEXTO,
    AUDIO,
    IMAGEM,
    VIDEO,
    TREINO_COMPARTILHADO
}

// QualidadePiso,Enum para avaliação do piso da quadra.
enum class QualidadePiso {
    EXCELENTE,
    BOM,
    REGULAR,
    RUIM
}

// TipoPerfil,Enumeração para diferenciar o usuário (para o Modo Treinador).
enum class TipoPerfil {
    ATLETA,
    TREINADOR
}
