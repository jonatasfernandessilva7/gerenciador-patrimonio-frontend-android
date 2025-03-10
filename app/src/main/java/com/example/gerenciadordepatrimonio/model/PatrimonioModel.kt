package com.example.gerenciadordepatrimonio.model

data class PatrimonioResponse(
    val patrimonio: Patrimonio
)

data class Patrimonio(
    val id: Int,
    val usuarioId: Int,
    val bens: Bens?,
    val direitos: List<Direito>?,
    val obrigacoes: List<Obrigacao>?
)

data class Bens(
    val patrimonioId: Int,
    val imoveis: List<Imovel>?,
    val terrenos: List<Terreno>?,
    val veiculos: List<Veiculo>?,
    val participacoes: List<Participacao>?,
    val outrosInvestimentos: List<OutroInvestimento>?
)

data class Imovel(
    val id: Int,
    val patrimonioId: Int,
    val descricao: String
)

data class Terreno(
    val id: Int,
    val patrimonioId: Int,
    val localizacao: String
)

data class Veiculo(
    val id: Int,
    val patrimonioId: Int,
    val modelo: String
)

data class Participacao(
    val id: Int,
    val patrimonioId: Int,
    val empresa: String
)

data class OutroInvestimento(
    val id: Int,
    val patrimonioId: Int,
    val tipo: String
)

data class Direito(
    val id: Int,
    val patrimonioId: Int,
    val valor: Double,
    val anoDeEntrada: Int
)

data class Obrigacao(
    val id: Int,
    val patrimonioId: Int,
    val valor: Double,
    val anoDeEntrada: Int
)