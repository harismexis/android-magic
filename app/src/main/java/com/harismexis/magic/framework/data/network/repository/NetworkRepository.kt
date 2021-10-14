package com.harismexis.magic.framework.data.network.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.network.retrofit.datasource.RetrofitDatasource
import com.harismexis.magic.framework.data.network.simple.NativeRemoteDatasource
import com.harismexis.magic.framework.extensions.card.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private val retrofit: RetrofitDatasource,
    private val nativeRemote: NativeRemoteDatasource
) : MagicRemote {

    override suspend fun getCards(name: String?): List<Card> = retrofit.getCards(name).toItems()

    override fun getCardsBlocking(name: String?): List<Card> =
        nativeRemote.getCardsBlocking(name).toItems()

    override suspend fun getCardsMainSafe(name: String?): List<Card> =
        nativeRemote.getCardsMainSafe(name).toItems()
}