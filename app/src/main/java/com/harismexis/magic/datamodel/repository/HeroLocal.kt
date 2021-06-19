package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Hero

interface HeroLocal {
    suspend fun updateHeros(items: List<Hero>)

    suspend fun getHero(itemId: Int): Hero?

    suspend fun getHeros(): List<Hero>

}