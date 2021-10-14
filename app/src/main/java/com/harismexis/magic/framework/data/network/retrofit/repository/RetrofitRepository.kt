package com.harismexis.magic.framework.data.network.retrofit.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.network.retrofit.datasource.RetrofitDatasource
import com.harismexis.magic.framework.extensions.card.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRepository @Inject constructor(
    private val datasource: RetrofitDatasource
): MagicRemote {

    override suspend fun getCards(name: String?): List<Card> = datasource.getCards(name).toItems()

}