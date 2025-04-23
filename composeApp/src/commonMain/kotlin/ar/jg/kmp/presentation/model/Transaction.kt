package ar.jg.kmp.presentation.model

data class Transaction(
    val id: String,
    val title: String,
    val date: String,
    val amount: Float,
    val currency: String,
    val type: TransactionType
)

enum class TransactionType {
    INCOME, EXPENSE
}