package pt.ismai.data

import pt.ismai.Categorias
import pt.ismai.Exercicio
import pt.ismai.MetodoAvalicao
import pt.ismai.NivelDificuldade

object ExerciseSeeder {

    // 1. Função que gera a lista completa baseada no seu documento
    fun getPredefinedExercises(): List<Exercicio> {
        return listOf(
            // --- BASE (7 Exercícios) ---
            Exercicio(
                id = "101",
                nome = "2 Pontos: 0º Esquerda",
                descricao = "Arremesso de 2 pontos no canto (corner) esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "102",
                nome = "2 Pontos: 0º Direita",
                descricao = "Arremesso de 2 pontos no canto (corner) direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "103",
                nome = "2 Pontos: 45º Esquerda",
                descricao = "Arremesso de 2 pontos a 45 graus do cesto, lado esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "104",
                nome = "2 Pontos: 45º Direita",
                descricao = "Arremesso de 2 pontos a 45 graus do cesto, lado direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "105",
                nome = "2 Pontos: 90º Esquerda",
                descricao = "Arremesso de 2 pontos frontal/lateral, lado esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "106",
                nome = "2 Pontos: 90º Direita",
                descricao = "Arremesso de 2 pontos frontal/lateral, lado direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "107",
                nome = "Lance Livre",
                descricao = "Arremesso livre da linha de falta.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            // --- VARIAÇÃO: STEP BACK ( 7 spots) ---
            Exercicio(
                id = "108",
                nome = "2 Pontos: 0º Esq + Step Back",
                descricao = "Arremesso no canto esquerdo criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "109",
                nome = "2 Pontos: 0º Dir + Step Back",
                descricao = "Arremesso no canto direito criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "110",
                nome = "2 Pontos: 45º Esq + Step Back",
                descricao = "Arremesso a 45º na esquerda criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "111",
                nome = "2 Pontos: 45º Dir + Step Back",
                descricao = "Arremesso a 45º na direita criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "112",
                nome = "2 Pontos: 90º Esq + Step Back",
                descricao = "Arremesso frontal à esquerda criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "113",
                nome = "2 Pontos: 90º Dir + Step Back",
                descricao = "Arremesso frontal à direita criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "113",
                nome = "Lance Livre + Step Back",
                descricao = "Arremesso frontal criando separação com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: STEP SIDE DIREITA (7 spots) ---
            Exercicio(
                id = "114",
                nome = "2 Pontos: 0º Esq + Step Side Dir",
                descricao = "Arremesso no canto esquerdo criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "115",
                nome = "2 Pontos: 0º Dir + Step Side Dir",
                descricao = "Arremesso no canto direito criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "116",
                nome = "2 Pontos: 45º Esq + Step Side Dir",
                descricao = "Arremesso a 45º na esquerda criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "117",
                nome = "2 Pontos: 45º Dir + Step Side Dir",
                descricao = "Arremesso a 45º na direita criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "118",
                nome = "2 Pontos: 90º Esq + Step Side Dir",
                descricao = "Arremesso frontal à esquerda criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "119",
                nome = "2 Pontos: 90º Dir + Step Side Dir",
                descricao = "Arremesso frontal à direita criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "119",
                nome = "Lance Livre + Step Side Dir",
                descricao = "Arremesso frontal criando separação com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- VARIAÇÃO: STEP SIDE ESQUERDA (7 spots) ---
            Exercicio(
                id = "120",
                nome = "2 Pontos: 0º Esq + Step Side Esq",
                descricao = "Arremesso no canto esquerdo criando separação com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "121",
                nome = "2 Pontos: 0º Dir + Step Side Esq",
                descricao = "Arremesso no canto direito com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "122",
                nome = "2 Pontos: 45º Esq + Step Side Esq",
                descricao = "Arremesso a 45º na esquerda com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "123",
                nome = "2 Pontos: 45º Dir + Step Side Esq",
                descricao = "Arremesso a 45º na direita com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "124",
                nome = "2 Pontos: 90º Esq + Step Side Esq",
                descricao = "Arremesso frontal à esquerda com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "125",
                nome = "2 Pontos: 90º Dir + Step Side Esq",
                descricao = "Arremesso frontal à direita com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "126",
                nome = "Lance Livre + Step Side Esq",
                descricao = "Arremesso de lance livre com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- VARIAÇÃO: GIRO LATERAL DIREITA (7 spots) ---
            Exercicio(
                id = "127",
                nome = "2 Pontos: 0º Esq + Giro Lateral Dir",
                descricao = "Arremesso no canto esquerdo precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "128",
                nome = "2 Pontos: 0º Dir + Giro Lateral Dir",
                descricao = "Arremesso no canto direito precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "129",
                nome = "2 Pontos: 45º Esq + Giro Lateral Dir",
                descricao = "Arremesso a 45º na esquerda precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "130",
                nome = "2 Pontos: 45º Dir + Giro Lateral Dir",
                descricao = "Arremesso a 45º na direita precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "131",
                nome = "2 Pontos: 90º Esq + Giro Lateral Dir",
                descricao = "Arremesso frontal à esquerda precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "132",
                nome = "2 Pontos: 90º Dir + Giro Lateral Dir",
                descricao = "Arremesso frontal à direita precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "133",
                nome = "Lance Livre + Giro Lateral Dir",
                descricao = "Arremesso de lance livre precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: GIRO LATERAL ESQUERDA (7 spots) ---
            Exercicio(
                id = "134",
                nome = "2 Pontos: 0º Esq + Giro Lateral Esq",
                descricao = "Arremesso no canto esquerdo precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "135",
                nome = "2 Pontos: 0º Dir + Giro Lateral Esq",
                descricao = "Arremesso no canto direito precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "136",
                nome = "2 Pontos: 45º Esq + Giro Lateral Esq",
                descricao = "Arremesso a 45º na esquerda precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "137",
                nome = "2 Pontos: 45º Dir + Giro Lateral Esq",
                descricao = "Arremesso a 45º na direita precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "138",
                nome = "2 Pontos: 90º Esq + Giro Lateral Esq",
                descricao = "Arremesso frontal à esquerda precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "139",
                nome = "2 Pontos: 90º Dir + Giro Lateral Esq",
                descricao = "Arremesso frontal à direita precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "140",
                nome = "Lance Livre + Giro Lateral Esq",
                descricao = "Arremesso de lance livre precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: FAKE PASS (7 spots) ---
            Exercicio(
                id = "141",
                nome = "2 Pontos: 0º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso no canto esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "142",
                nome = "2 Pontos: 0º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso no canto direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "143",
                nome = "2 Pontos: 45º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso a 45º na esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "144",
                nome = "2 Pontos: 45º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso a 45º na direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "145",
                nome = "2 Pontos: 90º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso frontal à esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "146",
                nome = "2 Pontos: 90º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso frontal à direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "147",
                nome = "Lance Livre + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso na linha de lance livre.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- VARIAÇÃO: RECEBER E CHUTAR (7 spots) ---
            Exercicio(
                id = "148",
                nome = "2 Pontos: 0º Esq + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe no canto esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "149",
                nome = "2 Pontos: 0º Dir + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe no canto direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "150",
                nome = "2 Pontos: 45º Esq + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe a 45º na esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "151",
                nome = "2 Pontos: 45º Dir + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe a 45º na direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "152",
                nome = "2 Pontos: 90º Esq + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe frontal à esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "153",
                nome = "2 Pontos: 90º Dir + Receber e Chutar",
                descricao = "Arremesso rápido após receber o passe frontal à direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "154",
                nome = "Lance Livre + Receber e Chutar",
                descricao = "Arremesso rápido na linha de lance livre após receber o passe.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- BASE: 3 PONTOS (7 spots) ---
            Exercicio(
                id = "155",
                nome = "3 Pontos: 0º Esquerda",
                descricao = "Arremesso de 3 pontos no canto (corner) esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "156",
                nome = "3 Pontos: 0º Direita",
                descricao = "Arremesso de 3 pontos no canto (corner) direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "157",
                nome = "3 Pontos: 45º Esquerda",
                descricao = "Arremesso de 3 pontos a 45 graus, lado esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "158",
                nome = "3 Pontos: 45º Direita",
                descricao = "Arremesso de 3 pontos a 45 graus, lado direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "159",
                nome = "3 Pontos: 90º Esquerda",
                descricao = "Arremesso de 3 pontos frontal à esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "160",
                nome = "3 Pontos: 90º Direita",
                descricao = "Arremesso de 3 pontos frontal à direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "161",
                nome = "3 Pontos: Direto",
                descricao = "Arremesso de 3 pontos frontal ao cesto (topo da chave).",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- VARIAÇÃO: 3 PONTOS + STEP BACK (7 spots) ---
            Exercicio(
                id = "162",
                nome = "3 Pontos: 0º Esq + Step Back",
                descricao = "Arremesso de 3 pontos no canto esquerdo com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "163",
                nome = "3 Pontos: 0º Dir + Step Back",
                descricao = "Arremesso de 3 pontos no canto direito com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "164",
                nome = "3 Pontos: 45º Esq + Step Back",
                descricao = "Arremesso de 3 pontos a 45º na esquerda com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "165",
                nome = "3 Pontos: 45º Dir + Step Back",
                descricao = "Arremesso de 3 pontos a 45º na direita com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "166",
                nome = "3 Pontos: 90º Esq + Step Back",
                descricao = "Arremesso de 3 pontos frontal à esquerda com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "167",
                nome = "3 Pontos: 90º Dir + Step Back",
                descricao = "Arremesso de 3 pontos frontal à direita com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "168",
                nome = "3 Pontos: Direto + Step Back",
                descricao = "Arremesso de 3 pontos frontal com passo atrás.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + STEP SIDE DIREITA (7 spots) ---
            Exercicio(
                id = "169",
                nome = "3 Pontos: 0º Esq + Step Side Dir",
                descricao = "Arremesso de 3 pontos no canto esquerdo com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "170",
                nome = "3 Pontos: 0º Dir + Step Side Dir",
                descricao = "Arremesso de 3 pontos no canto direito com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "171",
                nome = "3 Pontos: 45º Esq + Step Side Dir",
                descricao = "Arremesso de 3 pontos a 45º na esquerda com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "172",
                nome = "3 Pontos: 45º Dir + Step Side Dir",
                descricao = "Arremesso de 3 pontos a 45º na direita com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "173",
                nome = "3 Pontos: 90º Esq + Step Side Dir",
                descricao = "Arremesso de 3 pontos frontal à esquerda com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "174",
                nome = "3 Pontos: 90º Dir + Step Side Dir",
                descricao = "Arremesso de 3 pontos frontal à direita com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "175",
                nome = "3 Pontos: Direto + Step Side Dir",
                descricao = "Arremesso de 3 pontos frontal (topo) com passo lateral para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + STEP SIDE ESQUERDA (7 spots) ---
            Exercicio(
                id = "176",
                nome = "3 Pontos: 0º Esq + Step Side Esq",
                descricao = "Arremesso de 3 pontos no canto esquerdo com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "177",
                nome = "3 Pontos: 0º Dir + Step Side Esq",
                descricao = "Arremesso de 3 pontos no canto direito com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "178",
                nome = "3 Pontos: 45º Esq + Step Side Esq",
                descricao = "Arremesso de 3 pontos a 45º na esquerda com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "179",
                nome = "3 Pontos: 45º Dir + Step Side Esq",
                descricao = "Arremesso de 3 pontos a 45º na direita com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "180",
                nome = "3 Pontos: 90º Esq + Step Side Esq",
                descricao = "Arremesso de 3 pontos frontal à esquerda com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "181",
                nome = "3 Pontos: 90º Dir + Step Side Esq",
                descricao = "Arremesso de 3 pontos frontal à direita com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "182",
                nome = "3 Pontos: Direto + Step Side Esq",
                descricao = "Arremesso de 3 pontos frontal (topo) com passo lateral para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + GIRO LATERAL DIREITA (7 spots) ---
            Exercicio(
                id = "183",
                nome = "3 Pontos: 0º Esq + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos no canto esquerdo precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "184",
                nome = "3 Pontos: 0º Dir + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos no canto direito precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "185",
                nome = "3 Pontos: 45º Esq + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos a 45º na esquerda precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "186",
                nome = "3 Pontos: 45º Dir + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos a 45º na direita precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "187",
                nome = "3 Pontos: 90º Esq + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos frontal à esquerda precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "188",
                nome = "3 Pontos: 90º Dir + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos frontal à direita precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "189",
                nome = "3 Pontos: Direto + Giro Lateral Dir",
                descricao = "Arremesso de 3 pontos frontal (topo) precedido de giro para a direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + GIRO LATERAL ESQUERDA (7 spots) ---
            Exercicio(
                id = "190",
                nome = "3 Pontos: 0º Esq + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos no canto esquerdo precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "191",
                nome = "3 Pontos: 0º Dir + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos no canto direito precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "192",
                nome = "3 Pontos: 45º Esq + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos a 45º na esquerda precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "193",
                nome = "3 Pontos: 45º Dir + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos a 45º na direita precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "194",
                nome = "3 Pontos: 90º Esq + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos frontal à esquerda precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "195",
                nome = "3 Pontos: 90º Dir + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos frontal à direita precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "196",
                nome = "3 Pontos: Direto + Giro Lateral Esq",
                descricao = "Arremesso de 3 pontos frontal (topo) precedido de giro para a esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + FAKE PASS (7 spots) ---
            Exercicio(
                id = "197",
                nome = "3 Pontos: 0º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos no canto esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "198",
                nome = "3 Pontos: 0º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos no canto direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "199",
                nome = "3 Pontos: 45º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos a 45º na esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "200",
                nome = "3 Pontos: 45º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos a 45º na direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "201",
                nome = "3 Pontos: 90º Esq + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos frontal à esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "202",
                nome = "3 Pontos: 90º Dir + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos frontal à direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "203",
                nome = "3 Pontos: Direto + Fake Pass",
                descricao = "Simulação de passe seguida de arremesso de 3 pontos frontal (topo).",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- VARIAÇÃO: 3 PONTOS + RECEBER E CHUTAR (7 spots) ---
            Exercicio(
                id = "204",
                nome = "3 Pontos: 0º Esq + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe no canto esquerdo.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "205",
                nome = "3 Pontos: 0º Dir + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe no canto direito.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "206",
                nome = "3 Pontos: 45º Esq + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe a 45º na esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "207",
                nome = "3 Pontos: 45º Dir + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe a 45º na direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "208",
                nome = "3 Pontos: 90º Esq + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe frontal à esquerda.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "209",
                nome = "3 Pontos: 90º Dir + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe frontal à direita.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "210",
                nome = "3 Pontos: Direto + Receber e Chutar",
                descricao = "Arremesso rápido de 3 pontos após receber o passe no topo da chave.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: DE BAIXO DO CESTO (8 spots) ---
            Exercicio(
                id = "211",
                nome = "Gancho de Direita (Frente)",
                descricao = "Arremesso de gancho com a mão direita direcionado para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "212",
                nome = "Gancho de Esquerda (Frente)",
                descricao = "Arremesso de gancho com a mão esquerda direcionado para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "213",
                nome = "Tabelada de Esquerda",
                descricao = "Arremesso com a mão esquerda utilizando a tabela.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "214",
                nome = "Tabelada de Direita",
                descricao = "Arremesso com a mão direita utilizando a tabela.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "215",
                nome = "Gancho de Direita (Costas)",
                descricao = "Arremesso de gancho com a mão direita, posicionado de costas para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "216",
                nome = "Gancho de Esquerda (Costas)",
                descricao = "Arremesso de gancho com a mão esquerda, posicionado de costas para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "217",
                nome = "Gancho de Direita (Lado)",
                descricao = "Arremesso de gancho com a mão direita, posicionado de lado para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "218",
                nome = "Gancho de Esquerda (Lado)",
                descricao = "Arremesso de gancho com a mão esquerda, posicionado de lado para o cesto.",
                categoria = Categorias.ARREMESSO,
                metodoAvaliacao = MetodoAvalicao.POR_PRECISAO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            // --- CATEGORIA: FÍSICO - PEITO (8 Exercicios) ---
            Exercicio(
                id = "219",
                nome = "Flexões",
                descricao = "Flexões de braços clássicas para fortalecimento do peito.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "220",
                nome = "Flexões de Joelhos",
                descricao = "Variação de flexões com apoio dos joelhos para menor intensidade.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "221",
                nome = "Flexões Diamante",
                descricao = "Flexões com as mãos juntas formando um diamante, focando em tríceps e peito central.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "222",
                nome = "Flexões Diamante de Joelhos",
                descricao = "Variação mais leve da flexão diamante com apoio nos joelhos.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "223",
                nome = "Flexões Arqueiro",
                descricao = "Flexão lateral focando o peso em um braço de cada vez.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "224",
                nome = "Flexões com Palma",
                descricao = "Flexão explosiva com batida de palmas no ar.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "225",
                nome = "Flexões Unilaterais",
                descricao = "Flexão realizada com apenas um dos braços.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "226",
                nome = "Flexões Isométricas",
                descricao = "Manter a posição de flexão em um determinado ângulo.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: FÍSICO - COSTAS (2 exercícios) ---
            Exercicio(
                id = "227",
                nome = "Pull-up",
                descricao = "Elevações na barra com pega supinada.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "228",
                nome = "Pull-up Pronada",
                descricao = "Elevações na barra com as palmas viradas para fora.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: FÍSICO - BÍCEPS (4 exercícios) ---
            Exercicio(
                id = "229",
                nome = "Rosca com Mochila",
                descricao = "Flexão de bíceps utilizando uma mochila como carga.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "230",
                nome = "Rosca Pronada com Mochila",
                descricao = "Flexão de bíceps com as palmas viradas para baixo (foco no antebraço).",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "231",
                nome = "Rosca com Toalha + Peso",
                descricao = "Flexão de bíceps segurando uma toalha que envolve um peso.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "232",
                nome = "Rosca Pronada com Toalha + Peso",
                descricao = "Flexão de bíceps com pega pronada usando toalha para fortalecer o antebraço.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- Finalização: FÍSICO - BÍCEPS/COSTAS (1 exercícios) ---
            Exercicio(
                id = "233",
                nome = "Australian Pull-up",
                descricao = "Remada baixa em barra horizontal para fortalecimento de costas e bíceps.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: FÍSICO - TRÍCEPS (2 exercícios) ---
            Exercicio(
                id = "234",
                nome = "Bench Dips",
                descricao = "Mergulho em banco ou cadeira para foco no tríceps.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "235",
                nome = "Paralelas",
                descricao = "Mergulho em barras paralelas para força de tríceps e peitoral inferior.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: FÍSICO - PERNA (13 exercícios) ---
            Exercicio(
                id = "236",
                nome = "Agachamento Livre",
                descricao = "Agachamento clássico sem peso adicional.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "237",
                nome = "Agachamento Isométrico",
                descricao = "Manter a posição de agachamento (geralmente contra a parede) por tempo máximo.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "238",
                nome = "Agachamento com Salto",
                descricao = "Agachamento explosivo seguido de salto vertical.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "239",
                nome = "Agachamento com uma Perna",
                descricao = "Pistol squat, agachamento profundo realizado com apenas uma perna.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "240",
                nome = "Agachamento Búlgaro",
                descricao = "Agachamento unilateral com uma perna apoiada atrás em um banco.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "241",
                nome = "Afunda / Tesoura",
                descricao = "Passada estática focando em quadríceps e glúteos.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "242",
                nome = "Afunda Lateral",
                descricao = "Passada lateral focando na musculatura interna e externa da coxa.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "243",
                nome = "Afunda com Salto",
                descricao = "Variação explosiva da passada com troca de pernas no ar.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "244",
                nome = "Step-up",
                descricao = "Subida em banco ou plataforma elevada, alternando as pernas.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "245",
                nome = "Pular Degraus Grandes (Pés Juntos)",
                descricao = "Salto pliométrico subindo degraus ou plataformas com ambos os pés.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "246",
                nome = "Elevação de Panturrilha",
                descricao = "Extensão plantar para fortalecimento dos gémeos.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "247",
                nome = "Elevação de Panturrilha Unilateral",
                descricao = "Extensão plantar realizada em uma perna de cada vez.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: FÍSICO - ABDOMINAIS (Início - 11 exercícios) ---
            Exercicio(
                id = "248",
                nome = "Abdominais Clássico",
                descricao = "Exercício tradicional de flexão do tronco.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "249",
                nome = "Abdominais Curtos",
                descricao = "Crunch abdominal com amplitude reduzida focado na parte superior.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "250",
                nome = "Abdominais Infra",
                descricao = "Elevação de pernas esticadas para focar na parte inferior do abdómen.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "251",
                nome = "Abdominal Bicicleta",
                descricao = "Movimento alternado de cotovelo ao joelho oposto com rotação de tronco.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "252",
                nome = "Abdominal Oblíquo",
                descricao = "Foco na lateral do abdómen com flexão lateral do tronco.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "253",
                nome = "Abdominal Cruzado",
                descricao = "Abdominal com rotação levando o cotovelo ao joelho oposto.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "254",
                nome = "Prancha Frontal",
                descricao = "Sustentação isométrica do corpo apoiado nos antebraços e pés.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "255",
                nome = "Prancha Lateral",
                descricao = "Sustentação lateral focando nos oblíquos.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "256",
                nome = "Prancha Dinâmica (Troca Mãos/Ombros)",
                descricao = "Em posição de prancha alta, alternar o apoio entre mãos e antebraços.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "257",
                nome = "Prancha Dinâmica (Toque no Ombro)",
                descricao = "Em posição de prancha alta, tocar o ombro oposto com a mão de forma alternada.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "258",
                nome = "Leg Raise (Elevação de Pernas)",
                descricao = "Elevação das pernas esticadas partindo da posição deitada.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: FÍSICO - OMBRO (5 exercícios) ---
            Exercicio(
                id = "259",
                nome = "Wall Walk",
                descricao = "Caminhada pelas mãos subindo os pés na parede até ficar em pino.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "260",
                nome = "Elevação Lateral com Mochila",
                descricao = "Levantamento lateral dos braços usando mochila como carga para foco no deltoide médio.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "261",
                nome = "Elevação Frontal com Mochila",
                descricao = "Levantamento frontal dos braços usando mochila como carga para foco no deltoide anterior.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "262",
                nome = "Press Militar com Mochila",
                descricao = "Desenvolvimento de ombros empurrando a mochila acima da cabeça.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "263",
                nome = "Flexão Declinada",
                descricao = "Flexão com os pés elevados para focar na parte superior do peito e ombros.",
                categoria = Categorias.FISICO,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: CARDIO - CORRIDA (13 exercícios - POR_TEMPO) ---
            Exercicio(
                id = "264",
                nome = "Sprint",
                descricao = "Corrida em velocidade máxima em curta distância.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "265",
                nome = "Corrida de Longa Duração",
                descricao = "Corrida contínua para trabalhar a resistência aeróbica.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "266",
                nome = "Trote",
                descricao = "Corrida leve e constante para recuperação ou aquecimento.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "267",
                nome = "Corrida com Salto Unilateral",
                descricao = "Corrida alternando saltos explosivos em uma perna.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "268",
                nome = "Suicidas",
                descricao = "Exercício de ida e volta em distâncias progressivas na quadra.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "269",
                nome = "Corrida Lateral",
                descricao = "Deslocamento lateral mantendo a postura defensiva.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "270",
                nome = "Ziguezague",
                descricao = "Corrida mudando de direção rapidamente entre cones ou marcações.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "271",
                nome = "Caminhada",
                descricao = "Caminhada em ritmo acelerado para recuperação ativa.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "272",
                nome = "Polichinelos",
                descricao = "Exercício rítmico de saltos abrindo e fechando braços e pernas.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "273",
                nome = "Alpinista (Mountain Climbers)",
                descricao = "Movimento de corrida em posição de prancha alta.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "274",
                nome = "Burpees",
                descricao = "Exercício completo combinando agachamento, flexão e salto.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "275",
                nome = "Bicicleta (Cardio)",
                descricao = "Simulação de pedalada para resistência aeróbica.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "276",
                nome = "Pular Corda",
                descricao = "Trabalho de coordenação e resistência saltando corda.",
                categoria = Categorias.CARDIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MAXIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: DRIBLE E DOMÍNIO - ESTACIONÁRIO (6 exercícios) ---
            Exercicio(
                id = "277",
                nome = "Drible Baixo (Tornozelo): Mão Direita",
                descricao = "Dribles rápidos e fortes na altura do tornozelo com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "278",
                nome = "Drible Baixo (Tornozelo): Mão Esquerda",
                descricao = "Dribles rápidos e fortes na altura do tornozelo com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "279",
                nome = "Drible Médio (Cintura): Mão Direita",
                descricao = "Drible com foco em controle e ritmo na altura da cintura.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "280",
                nome = "Drible Médio (Cintura): Mão Esquerda",
                descricao = "Drible com foco em controle e ritmo na altura da cintura.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "281",
                nome = "Drible Alto (Ombro): Mão Direita",
                descricao = "Drible com força máxima na altura do ombro para controle de potência.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "282",
                nome = "Drible Alto (Ombro): Mão Esquerda",
                descricao = "Drible com força máxima na altura do ombro com a mão não dominante.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: DRIBLE E DOMÍNIO - BALL WRAPS (9 exercícios) ---
            Exercicio(
                id = "283",
                nome = "Ball Wrap: Cabeça (Horário)",
                descricao = "Circular a bola ao redor da cabeça no sentido horário sem deixá-la cair.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "284",
                nome = "Ball Wrap: Cabeça (Anti-horário)",
                descricao = "Circular a bola ao redor da cabeça no sentido anti-horário.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "285",
                nome = "Ball Wrap: Cintura (Horário)",
                descricao = "Circular a bola ao redor da cintura no sentido horário em velocidade.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "286",
                nome = "Ball Wrap: Cintura (Anti-horário)",
                descricao = "Circular a bola ao redor da cintura no sentido anti-horário.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "287",
                nome = "Ball Wrap: Pernas Juntas (Horário)",
                descricao = "Circular a bola ao redor de ambas as pernas juntas no sentido horário.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "288",
                nome = "Ball Wrap: Pernas Juntas (Anti-horário)",
                descricao = "Circular a bola ao redor de ambas as pernas juntas no sentido anti-horário.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "289",
                nome = "Ball Wrap: Lunge Perna Direita",
                descricao = "Circular a bola ao redor da perna direita em posição de afunda (ambos os sentidos).",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "290",
                nome = "Ball Wrap: Lunge Perna Esquerda",
                descricao = "Circular a bola ao redor da perna esquerda em posição de afunda (ambos os sentidos).",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "291",
                nome = "Figure 8 (Sem Drible): Frente para Trás",
                descricao = "Passar a bola entre as pernas descrevendo um oito, movendo de frente para trás.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "292",
                nome = "Figure 8 (Sem Drible): Trás para Frente",
                descricao = "Passar a bola entre as pernas descrevendo um oito, movendo de trás para frente.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: DRIBLE E DOMÍNIO - CROSSOVERS BÁSICOS (6 exercícios) ---
            Exercicio(
                id = "293",
                nome = "Crossover Frontal: Dir para Esq",
                descricao = "Mudança de direção simples pela frente do corpo, da direita para a esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "294",
                nome = "Crossover Frontal: Esq para Dir",
                descricao = "Mudança de direção simples pela frente do corpo, da esquerda para a direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "295",
                nome = "Between the Legs: Dir para Esq",
                descricao = "Passar a bola entre as pernas da direita para a esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "296",
                nome = "Between the Legs: Esq para Dir",
                descricao = "Passar a bola entre as pernas da esquerda para a direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "297",
                nome = "Behind the Back: Dir para Esq",
                descricao = "Passar a bola por trás das costas (Wrap) da direita para a esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "298",
                nome = "Behind the Back: Esq para Dir",
                descricao = "Passar a bola por trás das costas (Wrap) da esquerda para a direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: DRIBLE E DOMÍNIO - AVANÇADOS (7 exercícios) ---
            Exercicio(
                id = "299",
                nome = "In & Out: Mão Direita",
                descricao = "Simulação de crossover frontal sem trocar de mão, com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "300",
                nome = "In & Out: Mão Esquerda",
                descricao = "Simulação de crossover frontal sem trocar de mão, com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "301",
                nome = "In & Out + Crossover: Início Dir",
                descricao = "Combinação de In & Out seguida de crossover, começando na mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "302",
                nome = "In & Out + Crossover: Início Esq",
                descricao = "Combinação de In & Out seguida de crossover, começando na mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "303",
                nome = "Between the Legs + Behind the Back: D>E>D",
                descricao = "Sequência rápida de entre pernas e por trás das costas voltando para a mão inicial.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "304",
                nome = "Between the Legs + Behind the Back: E>D>E",
                descricao = "Sequência rápida de entre pernas e por trás das costas começando na mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "305",
                nome = "Double Crossover: Frente Baixa",
                descricao = "Crossover duplo rápido (Direita > Esquerda > Direita) mantendo a bola baixa.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: DRIBLE EM MOVIMENTO - VELOCIDADE E RECUO (4 exercícios) ---
            Exercicio(
                id = "306",
                nome = "Speed Dribble: Mão Direita",
                descricao = "Drible em velocidade máxima empurrando a bola à frente com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "307",
                nome = "Speed Dribble: Mão Esquerda",
                descricao = "Drible em velocidade máxima empurrando a bola à frente com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "308",
                nome = "Retreat Dribble: Mão Direita",
                descricao = "Drible de recuo protegendo a bola com o corpo usando a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "309",
                nome = "Retreat Dribble: Mão Esquerda",
                descricao = "Drible de recuo protegendo a bola com o corpo usando a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            // --- CATEGORIA: DRIBLE EM MOVIMENTO - ZIGUEZAGUE (5 exercícios) ---
            Exercicio(
                id = "310",
                nome = "Ziguezague: Mudança com Crossover",
                descricao = "Percurso em ziguezague com foco na explosão após a troca de direção frontal.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "311",
                nome = "Ziguezague: Mudança Between the Legs",
                descricao = "Percurso em ziguezague com foco em baixar o quadril na troca entre pernas.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "312",
                nome = "Ziguezague: Mudança Behind the Back",
                descricao = "Percurso em ziguezague com foco em proteger a bola mudando de direção por trás das costas.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "313",
                nome = "Ziguezague: Mudança com Spin Move (Dir)",
                descricao = "Percurso em ziguezague utilizando o giro (spin move) com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "314",
                nome = "Ziguezague: Mudança com Spin Move (Esq)",
                descricao = "Percurso em ziguezague utilizando o giro (spin move) com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: DOMÍNIO E FINALIZAÇÃO - POCKET DRIBLE (3 exercícios) ---
            Exercicio(
                id = "315",
                nome = "Pocket Dribble Lateral: Mão Direita",
                descricao = "Treino de esconder a bola no 'bolso' (puxar para o quadril) com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "316",
                nome = "Pocket Dribble Lateral: Mão Esquerda",
                descricao = "Treino de esconder a bola no 'bolso' (puxar para o quadril) com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "317",
                nome = "Pocket Dribble + Jab Step",
                descricao = "Combinação de drible de proteção com passo lateral (jab step) oposto.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: DOMÍNIO E FINALIZAÇÃO - HESITATION (3 exercícios) ---
            Exercicio(
                id = "318",
                nome = "Hesitation: Stutter Step (Dir)",
                descricao = "Utilizar passos curtos e rápidos para hesitação com a mão direita.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "319",
                nome = "Hesitation: Stutter Step (Esq)",
                descricao = "Utilizar passos curtos e rápidos para hesitação com a mão esquerda.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "320",
                nome = "Hesitation: Freeze",
                descricao = "Parada total momentânea levantando o tronco sem interromper o drible.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: VARIAÇÕES DE DIFICULDADE (5 exercício) ---
            Exercicio(
                id = "321",
                nome = "Duas Bolas: Drible Simultâneo",
                descricao = "Driblar duas bolas ao mesmo tempo mantendo o mesmo ritmo.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "322",
                nome = "Duas Bolas: Drible Alternado",
                descricao = "Driblar duas bolas alternando o ritmo entre elas.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "323",
                nome = "Drible com Bola de Ténis",
                descricao = "Driblar a bola de basquete com uma mão enquanto lança e apanha uma bola de ténis com a outra.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "324",
                nome = "Drible com Olhos Fechados",
                descricao = "Treino de drible focando totalmente na sensibilidade tátil e controlo espacial sem usar a visão.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "325",
                nome = "Drible com Luvas / Saco Plástico",
                descricao = "Driblar com a bola envolvida em plástico ou usando luvas para reduzir a aderência.",
                categoria = Categorias.DRIBLE_DOMINIO,
                metodoAvaliacao = MetodoAvalicao.POR_TEMPO_MINIMO,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            // --- CATEGORIA: PASSE (9 exercícios) ---
            Exercicio(
                id = "326",
                nome = "Passe Alto",
                descricao = "Passe executado acima da cabeça para superar defesas altas.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "327",
                nome = "Passe de Peito",
                descricao = "Passe clássico de basquetebol saindo da altura do peito com ambas as mãos.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "328",
                nome = "Passe Picado",
                descricao = "Passe onde a bola bate no chão antes de chegar ao colega.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.FACIL
            ),
            Exercicio(
                id = "329",
                nome = "Passe Cruzado de Esquerda",
                descricao = "Passe lateral rápido utilizando apenas a mão esquerda.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "330",
                nome = "Passe Cruzado de Direita",
                descricao = "Passe lateral rápido utilizando apenas a mão direita.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "331",
                nome = "Passe por Trás das Costas",
                descricao = "Passe avançado executado passando a bola por trás do tronco.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "332",
                nome = "Pick and Pass",
                descricao = "Passe executado rapidamente após uma situação de bloqueio (pick).",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.MEDIA
            ),
            Exercicio(
                id = "333",
                nome = "Fake Pass and Pass",
                descricao = "Simular um passe numa direção e executar o passe real noutra direção.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            ),
            Exercicio(
                id = "334",
                nome = "Fake Shot and Pass",
                descricao = "Simular um arremesso (pump fake) para atrair a defesa e passar a bola.",
                categoria = Categorias.PASSE,
                metodoAvaliacao = MetodoAvalicao.POR_REPETICOES,
                nivelDificuldade = NivelDificuldade.DIFICIO
            )
        )
    }

    suspend fun uploadAllExercises(dbManager: DatabaseManager) {
        val exercises = getPredefinedExercises()

        for (exercicio in exercises) {
            dbManager.saveNativeExercise(exercicio)
        }
    }
}