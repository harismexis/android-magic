package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Card

interface MagicRemote {
    suspend fun getHeros(name: String? = null): List<Card>
}