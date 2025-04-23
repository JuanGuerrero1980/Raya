package ar.jg.kmp.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ar.jg.kmp.presentation.ui.BalanceCard
import ar.jg.kmp.presentation.ui.BottomNavigationBar
import ar.jg.kmp.presentation.ui.Screen
import ar.jg.kmp.presentation.ui.TransactionList
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import raya.composeapp.generated.resources.Res
import raya.composeapp.generated.resources.home_convert_currencies

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    onConvertClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        val state by viewModel.state.collectAsState()
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            bottomBar = { BottomNavigationBar() }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF9FAFB))
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
            ) {
                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Placeholder for user avatar
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "User Avatar",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Welcome back", style = MaterialTheme.typography.titleMedium)
                            Text("John Doe", fontWeight = FontWeight.Bold)
                        }
                    }
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }

                BalanceCard(
                    currentBalance = state.currentBalance,
                    currencies = viewModel.balances.keys.toList(),
                    selectedCurrency = state.selectedCurrency,
                    onCurrencySelected = { viewModel.onCurrencySelected(it) }
                )

                Button(
                    onClick = { onConvertClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(stringResource(Res.string.home_convert_currencies))
                }

                TransactionList(state.simpleTransactions)

            }
        }
    }

}

