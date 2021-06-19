package com.harismexis.magic.framework.datasource.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.magic.framework.datasource.database.table.LocalCard

@Dao
interface MagicLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeros(items: List<LocalCard>)

    @Query("SELECT * FROM magic_cards_table WHERE id = :itemId")
    suspend fun getHeroById(itemId: String): LocalCard?

    @Query("SELECT * FROM magic_cards_table")
    suspend fun getAllHeros(): List<LocalCard?>?

    @Query("DELETE FROM magic_cards_table")
    suspend fun deleteAllHeros()

}
