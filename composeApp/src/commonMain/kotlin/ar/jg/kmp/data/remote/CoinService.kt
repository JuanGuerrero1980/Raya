package ar.jg.kmp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoinService(private val client: HttpClient) {

    suspend fun getCoinGeckoPrice(): CoinGeckoPrice =
            client.get("exchange_rates").body()

}
