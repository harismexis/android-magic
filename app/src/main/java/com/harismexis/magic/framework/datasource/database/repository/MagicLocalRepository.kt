package com.harismexis.magic.framework.datasource.database.repository

import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.repository.MagicLocal
import com.harismexis.magic.framework.datasource.database.data.RickAndMortyLocalDao
import com.harismexis.magic.framework.extensions.hero.toItem
import com.harismexis.magic.framework.extensions.hero.toItems
import com.harismexis.magic.framework.extensions.hero.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MagicLocalRepository @Inject constructor(
    private val dao: RickAndMortyLocalDao
): MagicLocal {
    override suspend fun updateHeros(items: List<Card>) {
        dao.deleteAllHeros()
        dao.insertHeros(items.toLocalItems())
    }

    override suspend fun getHero(itemId: Int): Card? {
        val localItem = dao.getHeroById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getHeros(): List<Card> {
        return dao.getAllHeros().toItems()
    }

}