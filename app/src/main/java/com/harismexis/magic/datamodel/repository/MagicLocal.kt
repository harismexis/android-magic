package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Card

interface MagicLocal {
    suspend fun updateCards(items: List<Card>)

    suspend fun getCard(id: String): Card?

    suspend fun getCards(): List<Card>

}