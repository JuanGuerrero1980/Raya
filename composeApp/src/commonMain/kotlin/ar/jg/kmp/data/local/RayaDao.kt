package ar.jg.kmp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.jg.kmp.data.Balance

@Dao
interface RayaDao {

    @Query("SELECT * FROM Balance")
    suspend fun fetchBalances(): List<Balance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movies: List<Balance>)
}