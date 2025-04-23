package ar.jg.kmp.domain

import ar.jg.kmp.data.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetBalanceUseCase(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(): Map<String, Float> {
        return withContext(Dispatchers.IO) {
            val balances = coinRepository.getBalances().map {
                it.id to it.amount
            }
            balances.toMap()
        }
    }
}
