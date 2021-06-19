package com.harismexis.magic.framework.datasource.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harismexis.magic.framework.datasource.database.converter.Converter
import com.harismexis.magic.framework.datasource.database.data.MagicLocalDao
import com.harismexis.magic.framework.datasource.database.table.LocalCard

@Database(
    entities = [
        LocalCard::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MagicDatabase : RoomDatabase() {

    companion object {
        @Volatile
        var INSTANCE: MagicDatabase? = null
        private const val DATABASE_FILE_NAME = "magic_database"

        fun getDatabase(
            context: Context
        ): MagicDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MagicDatabase::class.java,
                    DATABASE_FILE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): MagicLocalDao

}
