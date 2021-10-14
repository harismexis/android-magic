package com.harismexis.magic.core.repository

import com.harismexis.magic.core.domain.Card

interface MagicRemote {
    suspend fun getCards(name: String? = null): List<Card>

    fun getCardsBlocking(name: String? = null): List<Card>
}