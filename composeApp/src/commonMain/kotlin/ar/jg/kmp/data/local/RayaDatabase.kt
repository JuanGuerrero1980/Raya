package ar.jg.kmp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import ar.jg.kmp.data.Balance

const val DATABASE_NAME = "raya.db"

interface DB {
    fun clearAllTables() {}
}

@Database(entities = [Balance::class], version = 1, exportSchema = false)
abstract class RayaDatabase : RoomDatabase(), DB {
    abstract fun rayaDao(): RayaDao

    override fun clearAllTables() { }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<RayaDatabase>
): RayaDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .build()
}