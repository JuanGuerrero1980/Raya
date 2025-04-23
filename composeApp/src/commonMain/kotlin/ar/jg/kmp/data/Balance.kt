package ar.jg.kmp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Balance(
    @PrimaryKey
    val id: String,
    val amount: Float,
)
