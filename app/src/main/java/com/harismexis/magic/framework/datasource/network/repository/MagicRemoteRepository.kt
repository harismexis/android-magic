package com.harismexis.magic.framework.datasource.network.repository

import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.repository.MagicRemote
import com.harismexis.magic.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.magic.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class MagicRemoteRepository @Inject constructor(
    private val dao: RickAndMortyRemoteDao
): MagicRemote {
    override suspend fun getHeros(name: String?): List<Card> = dao.getHeros(name).toItems()
}