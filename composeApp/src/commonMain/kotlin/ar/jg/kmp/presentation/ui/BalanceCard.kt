package ar.jg.kmp.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import raya.composeapp.generated.resources.Res
import raya.composeapp.generated.resources.home_total_balance

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BalanceCard(
    currentBalance: Float,
    currencies: List<String>,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    stringResource(Res.string.home_total_balance),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                CurrencyDropdown(
                    currencies = currencies,
                    selectedCurrency = selectedCurrency,
                    onCurrencySelected = { onCurrencySelected(it) }
                )
            }
            val balance = currentBalance.toString()
            Row(verticalAlignment = Alignment.CenterVertically) {
                AnimatedContent(
                    targetState = "$balance $selectedCurrency",
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) with fadeOut(
                            animationSpec = tween(
                                300
                            )
                        )
                    },
                    label = "BalanceAnimation"
                ) { animatedBalance ->
                    Text(
                        text = animatedBalance,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}