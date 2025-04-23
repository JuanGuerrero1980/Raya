package ar.jg.kmp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.jg.kmp.domain.GetBalanceUseCase
import ar.jg.kmp.domain.GetSimpleTransactionsUseCase
import ar.jg.kmp.domain.SaveBalanceUseCase
import ar.jg.kmp.presentation.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.String
import kotlin.collections.Map

class HomeViewModel(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val saveBalanceUseCase: SaveBalanceUseCase,
    private val getTransactionsUseCase: GetSimpleTransactionsUseCase,
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    var balances = mapOf<String, Float>()

    init {
        viewModelScope.launch {
            saveBalanceUseCase()
            balances = getBalanceUseCase()
            _state.value = _state.value.copy(
                currentBalance = balances[_state.value.selectedCurrency] ?: 0f,
                simpleTransactions = getTransactionsUseCase()
            )
        }
    }

    fun onCurrencySelected(currency: String) {
        _state.value = _state.value.copy(
            selectedCurrency = currency,
            currentBalance = balances[currency] ?: 0f,
        )
    }

}

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: Map<String, Double> = mapOf<String, Double>(),
    var selectedCurrency: String = "USD",
    var currentBalance: Float = 0f,
    var simpleTransactions: List<Transaction> = listOf()
    )