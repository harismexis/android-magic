package com.harismexis.magic.framework.datasource.network.repository

import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.repository.MagicRemote
import com.harismexis.magic.framework.datasource.network.data.MagicRemoteDatasource
import com.harismexis.magic.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class MagicRemoteRepository @Inject constructor(
    private val datasource: MagicRemoteDatasource
): MagicRemote {
    override suspend fun getCards(name: String?): List<Card> = datasource.getCards(name).toItems()
}