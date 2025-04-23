package ar.jg.kmp.presentation.conversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.jg.kmp.common.Result
import ar.jg.kmp.domain.GetBalanceUseCase
import ar.jg.kmp.domain.GetCoinPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ConversionViewModel(
    private val getCoinPriceUseCase: GetCoinPriceUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(ConversionUiState())
    val uiState = _uiState.asStateFlow()
    var balances = mapOf<String, Float>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
                balances = getBalanceUseCase()
            _uiState.value = _uiState.value.copy(
                currentBalance = balances[_uiState.value.fromCurrency] ?: 0f
            )
        }
    }

    fun setToCurrency(value: String) {
        _uiState.update { it.copy(toCurrency = value) }
    }

    fun convertCurrency(
        toCurrency: String,
    ) {
        setToCurrency(toCurrency)
        val amount = _uiState.value.currentBalance
        val fromRateInBTC = _uiState.value.data[_uiState.value.fromCurrency.uppercase()]?: 0.0
        val toRateInBTC = _uiState.value.data[_uiState.value.toCurrency.uppercase()]?: 0.0

        val amountInBTC = amount / fromRateInBTC
        _uiState.update { it.copy(result = amountInBTC * toRateInBTC) }
    }

    fun onCurrencySelected(currency: String) {
        _uiState.value = _uiState.value.copy(
            fromCurrency = currency,
            currentBalance = balances[currency] ?: 0f,
        )
        convertCurrency(_uiState.value.toCurrency)
    }

    fun loadCoinPrice() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
            )
            val price = getCoinPriceUseCase()
            when (price) {
                is Result.Success -> {
                    val p: Map<String, Double> = mapOf<String, Double> (
                        "BTC" to (price.data.rates["btc"]?.value ?: 0.0),
                        "ETH" to (price.data.rates["eth"]?.value ?: 0.0),
                        "USD" to (price.data.rates["usd"]?.value ?: 0.0),
                        "ARS" to (price.data.rates["ars"]?.value ?: 0.0)
                    )
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        data = p
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = price.message
                    )
                }
            }
        }
    }
}

data class ConversionUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var currentBalance: Float = 0f,
    val fromCurrency: String = "USD",
    val toCurrency: String = "USD",
    val result: Double? = null,
    var data: Map<String, Double> = mapOf<String, Double>(),
)