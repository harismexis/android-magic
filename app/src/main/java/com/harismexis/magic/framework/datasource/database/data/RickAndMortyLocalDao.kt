package com.harismexis.magic.framework.datasource.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harismexis.magic.framework.datasource.database.table.LocalHero

@Dao
interface RickAndMortyLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeros(items: List<LocalHero>)

    @Query("SELECT * FROM rick_and_morty_character_table WHERE id = :itemId")
    suspend fun getHeroById(itemId: Int): LocalHero?

    @Query("SELECT * FROM rick_and_morty_character_table")
    suspend fun getAllHeros(): List<LocalHero?>?

    @Query("DELETE FROM rick_and_morty_character_table")
    suspend fun deleteAllHeros()

}
