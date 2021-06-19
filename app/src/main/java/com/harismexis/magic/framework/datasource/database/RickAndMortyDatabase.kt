package com.harismexis.magic.framework.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harismexis.magic.framework.datasource.database.converter.Converter
import com.harismexis.magic.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.magic.framework.datasource.database.table.LocalHero

@Database(
    entities = [
        LocalHero::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: RickAndMortyDatabase? = null
        private const val DATABASE_FILE_NAME = "rick_and_morty_room_database"

        fun getDatabase(
            context: Context
        ): RickAndMortyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RickAndMortyDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): RickAndMortyLocalDao

}
