package com.harismexis.magic.framework.datasource.database.repository

import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.datamodel.repository.MagicLocal
import com.harismexis.magic.framework.datasource.database.data.MagicLocalDao
import com.harismexis.magic.framework.extensions.hero.toItem
import com.harismexis.magic.framework.extensions.hero.toItems
import com.harismexis.magic.framework.extensions.hero.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MagicLocalRepository @Inject constructor(
    private val dao: MagicLocalDao
): MagicLocal {

    override suspend fun updateCards(items: List<Card>) {
        dao.deleteAllCards()
        dao.insertCards(items.toLocalItems())
    }

    override suspend fun getCard(id: String): Card? {
        val localItem = dao.getCard(id)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getCards(): List<Card> {
        return dao.getAllCards().toItems()
    }

}