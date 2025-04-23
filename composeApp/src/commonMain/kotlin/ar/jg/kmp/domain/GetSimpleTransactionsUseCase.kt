package ar.jg.kmp.domain

import ar.jg.kmp.presentation.model.Transaction
import ar.jg.kmp.presentation.model.TransactionType

class GetSimpleTransactionsUseCase() {

    operator fun invoke(): List<Transaction> {
        // Simulate mock data
        return listOf(
            Transaction("1", "Pago recibido", "18/04/2025", 3000f, "ARS", TransactionType.INCOME),
            Transaction(
                "2",
                "Compra supermercado",
                "17/04/2025",
                1500.50f,
                "ARS",
                TransactionType.EXPENSE
            ),
            Transaction(
                "3",
                "Transferencia entrante",
                "16/04/2025",
                0.015f,
                "BTC",
                TransactionType.INCOME
            ),
            Transaction(
                "4",
                "Suscripción Netflix",
                "15/04/2025",
                8.99f,
                "USD",
                TransactionType.EXPENSE
            )
        )
    }
}