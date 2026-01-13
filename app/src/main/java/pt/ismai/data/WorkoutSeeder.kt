package pt.ismai.data

import pt.ismai.*
import java.util.Date
import kotlin.time.Duration.Companion.minutes

object WorkoutSeeder {

    fun getPredefinedWorkouts(): List<Treino> {
        // Recuperamos a lista total de exercícios para filtrar os que precisamos
        val allEx = ExerciseSeeder.getPredefinedExercises()

        return listOf(
            // 1. Treino de Fundamentos de Arremesso (Fácil)
            Treino(
                id = "1001",
                nome = "Fundamentos de Arremesso",
                descricao = "Rotina básica focada em mecânica de 2 pontos e ganchos junto ao cesto.",
                categorias = listOf(Categorias.ARREMESSO),
                nivelDificuldade = NivelDificuldade.FACIL,
                duracao = 30.minutes,
                exercicios = allEx.filter { it.id.toInt() in 101..107 || it.id.toInt() in 211..214 },
                dataCriacao = Date()
            ),

            // 2. Mestre do Drible e Controle (Dificuldade Média/Alta)
            Treino(
                id = "1002",
                nome = "Mestre do Drible",
                descricao = "Desenvolvimento de drible estacionário, em movimento e mudanças de direção.",
                categorias = listOf(Categorias.DRIBLE_DOMINIO),
                nivelDificuldade = NivelDificuldade.MEDIA,
                duracao = 45.minutes,
                exercicios = allEx.filter { it.id.toInt() in 277..282 || it.id.toInt() in 293..298 || it.id.toInt() == 305 },
                dataCriacao = Date()
            ),

            // 3. Condicionamento Físico de Elite (Difícil)
            Treino(
                id = "1003",
                nome = "Condicionamento de Atleta",
                descricao = "Treino intensivo combinando cardio, força de pernas e explosão.",
                categorias = listOf(Categorias.CARDIO, Categorias.FISICO),
                nivelDificuldade = NivelDificuldade.DIFICIO,
                duracao = 60.minutes,
                exercicios = allEx.filter { it.id.toInt() in listOf(238, 243, 264, 268, 274, 290) },
                dataCriacao = Date()
            ),

            // 4. Especialista em 3 Pontos (NOVO)
            Treino(
                id = "1004",
                nome = "Especialista em 3 Pontos",
                descricao = "Volume de arremesso de longa distância com foco em spots base e receção.",
                categorias = listOf(Categorias.ARREMESSO),
                nivelDificuldade = NivelDificuldade.DIFICIO,
                duracao = 40.minutes,
                exercicios = allEx.filter { it.id.toInt() in 155..161 || it.id.toInt() in 204..210 },
                dataCriacao = Date()
            ),

        // 5. Explosão e Finalização para Bases (NOVO)
            Treino(
                id = "1005",
                nome = "Explosão e Finalização",
                descricao = "Combinação de dribles agressivos com finalizações técnicas na tabela.",
                categorias = listOf(Categorias.DRIBLE_DOMINIO, Categorias.ARREMESSO),
                nivelDificuldade = NivelDificuldade.MEDIA,
                duracao = 35.minutes,
                exercicios = allEx.filter { it.id.toInt() in 293..314 || it.id.toInt() in 213..214 },
                dataCriacao = Date()
            )
        )
    }

    suspend fun uploadAllWorkouts(dbManager: DatabaseManager) {
        val workouts = getPredefinedWorkouts()

        for (treino in workouts) {
            dbManager.saveNativeWorkout(treino)
        }
    }
}