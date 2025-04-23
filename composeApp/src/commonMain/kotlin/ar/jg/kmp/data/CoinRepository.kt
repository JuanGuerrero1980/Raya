package ar.jg.kmp.data

import ar.jg.kmp.common.Result
import ar.jg.kmp.data.local.RayaDao
import ar.jg.kmp.data.remote.CoinGeckoPrice
import ar.jg.kmp.data.remote.CoinService

class CoinRepository(
    private val coinService: CoinService,
    private val coinDao: RayaDao
) {

    suspend fun getBalances() = coinDao.fetchBalances()

    suspend fun getCoinPrice(): Result<CoinGeckoPrice> {
        return try {
            val coinPrice = coinService.getCoinGeckoPrice()
            Result.Success(coinPrice)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun saveBalances(balances: List<Balance>) {
        coinDao.save(balances)
    }
}