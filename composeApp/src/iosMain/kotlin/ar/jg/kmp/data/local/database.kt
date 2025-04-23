package ar.jg.kmp.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<RayaDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<RayaDatabase>(
        name = dbFilePath,
        factory =  { RayaDatabase::class.instantiateImpl() }
    )
}