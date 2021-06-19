package com.harismexis.magic.framework.datasource.database.repository

import com.harismexis.magic.datamodel.domain.Hero
import com.harismexis.magic.datamodel.repository.HeroLocal
import com.harismexis.magic.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.magic.framework.extensions.hero.toItem
import com.harismexis.magic.framework.extensions.hero.toItems
import com.harismexis.magic.framework.extensions.hero.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroLocalRepository @Inject constructor(
    private val dao: RickAndMortyLocalDao
): HeroLocal {
    override suspend fun updateHeros(items: List<Hero>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    override suspend fun getHero(itemId: Int): Hero? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getHeros(): List<Hero> {
        return dao.getAllHeros().toItems()
    }

}