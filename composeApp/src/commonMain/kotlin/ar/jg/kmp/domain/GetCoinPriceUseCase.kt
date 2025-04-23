package ar.jg.kmp.domain

import ar.jg.kmp.common.Result
import ar.jg.kmp.data.CoinRepository
import ar.jg.kmp.data.remote.CoinGeckoPrice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetCoinPriceUseCase(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(): Result<CoinGeckoPrice> {
        return withContext(Dispatchers.IO) {
            coinRepository.getCoinPrice()
        }
    }
}
