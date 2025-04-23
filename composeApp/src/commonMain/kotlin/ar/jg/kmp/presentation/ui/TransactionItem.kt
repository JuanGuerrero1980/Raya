package ar.jg.kmp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.jg.kmp.presentation.model.Transaction
import ar.jg.kmp.presentation.model.TransactionType

@Composable
fun TransactionItem(transaction: Transaction) {
    val amountColor = if (transaction.type == TransactionType.INCOME) Color(0xFF357a38) else Color(0xFFF44336)
    val sign = if (transaction.type == TransactionType.INCOME) "+" else "-"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (transaction.type == TransactionType.INCOME) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = amountColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(transaction.title, fontWeight = FontWeight.SemiBold)
            Text(transaction.date, fontSize = 12.sp, color = Color.Gray)
        }

        Text(
            text = "$sign${transaction.amount} ${transaction.currency}",
            color = amountColor,
            fontWeight = FontWeight.Medium
        )
    }
}