package ar.jg.kmp.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class CoinGeckoPrice(
    val rates: Map<String, CoinRate>
)

@Serializable
data class CoinRate(
    val name: String,
    val unit: String,
    val value: Double,
    val type: String
)