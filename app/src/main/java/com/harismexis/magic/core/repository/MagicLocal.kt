package com.harismexis.magic.core.repository

import com.harismexis.magic.core.domain.Card

interface MagicLocal {
    suspend fun updateCards(items: List<Card>)

    suspend fun getCard(id: String): Card?

    suspend fun getCards(): List<Card>

}