package com.harismexis.magic.core.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.domain.Magic
import com.harismexis.magic.core.domain.Set

interface MagicRemote {

    suspend fun getCards(name: String? = null): List<Card>

    fun getCardsBlocking(name: String? = null): List<Card>

    suspend fun getCardsMainSafe(name: String? = null): List<Card>

    fun getSetsBlocking(name: String? = null): List<Set>

    suspend fun getSetsMainSafe(name: String? = null): List<Set>

    suspend fun getMagic(): Magic
}