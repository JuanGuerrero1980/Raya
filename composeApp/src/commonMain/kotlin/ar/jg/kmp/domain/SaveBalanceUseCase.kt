package ar.jg.kmp.domain

import ar.jg.kmp.data.Balance
import ar.jg.kmp.data.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SaveBalanceUseCase (
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke() {
         withContext(Dispatchers.IO) {
            coinRepository.saveBalances(getBalance())
        }
    }

    private fun getBalance(): List<Balance> {
        val fakeBalances = mapOf(
            "ETH" to Pair("0", "0"),
            "ARS" to Pair("863200", "00"),
            "BTC" to Pair("0", "045"),
            "USD" to Pair("2400", "75")
        )
        val balances = fakeBalances.map { (currency, balance) ->
            val (integerPart, decimalPart) = balance
            val amount = "$integerPart.$decimalPart".toFloat()
            Balance(currency, amount)
        }
        return balances
    }
}