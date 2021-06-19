package com.harismexis.magic.framework.datasource.network.repository

import com.harismexis.magic.datamodel.domain.Hero
import com.harismexis.magic.datamodel.repository.HeroRemote
import com.harismexis.magic.framework.datasource.network.data.RickAndMortyRemoteDao
import com.harismexis.magic.framework.extensions.hero.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class HeroRemoteRepository @Inject constructor(
    private val dao: RickAndMortyRemoteDao
): HeroRemote {
    override suspend fun getHeros(name: String?): List<Hero> = dao.getHeros(name).toItems()
}