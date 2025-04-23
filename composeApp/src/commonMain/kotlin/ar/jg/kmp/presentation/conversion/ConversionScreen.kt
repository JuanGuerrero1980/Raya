package ar.jg.kmp.presentation.conversion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import ar.jg.kmp.presentation.ui.AnimatedNumber
import ar.jg.kmp.presentation.ui.BalanceCard
import ar.jg.kmp.presentation.ui.CurrencyDropdown
import ar.jg.kmp.presentation.ui.LoadingIndicator
import ar.jg.kmp.presentation.ui.Screen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import raya.composeapp.generated.resources.Res
import raya.composeapp.generated.resources.back
import raya.composeapp.generated.resources.convert_title
import raya.composeapp.generated.resources.convert_to
import raya.composeapp.generated.resources.exchange_error

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun ConversionScreen(
    onBack: () -> Unit,
    viewModel: ConversionViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(Unit) {
        viewModel.loadCoinPrice()
    }

    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = stringResource(Res.string.convert_title),
                    scrollBehavior = scrollBehavior,
                    onBack = onBack
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LoadingIndicator(
                    enabled = state.isLoading,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                )
                if (state.isLoading) {
                    return@Scaffold
                }
                if(state.error != null) {
                    Text(
                        text = stringResource(Res.string.exchange_error),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    )
                    return@Scaffold
                }
                BalanceCard(
                    currentBalance = state.currentBalance,
                    currencies = viewModel.balances.keys.toList(),
                    selectedCurrency = state.fromCurrency,
                    onCurrencySelected = { viewModel.onCurrencySelected(it) }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = stringResource(Res.string.convert_to),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    CurrencyDropdown(
                        currencies = viewModel.balances.keys.toList(),
                        selectedCurrency = state.toCurrency,
                        onCurrencySelected = { viewModel.convertCurrency(it) }
                    )
                }
                AnimatedNumber(
                    number = "${(state.result?.toFloat()?:0f)} ${state.toCurrency.uppercase()}",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    durationMillis = 500)

            }
        }
    }

}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}