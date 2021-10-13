package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Card

interface MagicRemote {
    suspend fun getCards(name: String? = null): List<Card>
}