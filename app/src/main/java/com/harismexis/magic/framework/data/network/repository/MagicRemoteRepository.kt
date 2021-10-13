package com.harismexis.magic.framework.data.network.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.network.datasource.MagicRemoteDatasource
import com.harismexis.magic.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class MagicRemoteRepository @Inject constructor(
    private val datasource: MagicRemoteDatasource
): MagicRemote {
    override suspend fun getCards(name: String?): List<Card> = datasource.getCards(name).toItems()
}