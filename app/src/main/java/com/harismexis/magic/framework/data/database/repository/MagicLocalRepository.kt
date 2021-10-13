package com.harismexis.magic.framework.data.database.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.repository.MagicLocal
import com.harismexis.magic.framework.data.database.datasource.MagicLocalDao
import com.harismexis.magic.framework.extensions.card.toItem
import com.harismexis.magic.framework.extensions.card.toItems
import com.harismexis.magic.framework.extensions.card.toLocalItems
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