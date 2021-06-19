package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Card

interface MagicLocal {
    suspend fun updateHeros(items: List<Card>)

    suspend fun getHero(itemId: Int): Card?

    suspend fun getHeros(): List<Card>

}