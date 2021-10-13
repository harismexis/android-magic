package com.harismexis.magic.framework.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harismexis.magic.framework.data.database.converter.Converter
import com.harismexis.magic.framework.data.database.datasource.MagicLocalDao
import com.harismexis.magic.framework.data.database.table.LocalCard

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
