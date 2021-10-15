package com.harismexis.magic.framework.data.network.repository

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.domain.Set
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.network.model.cards.toCards
import com.harismexis.magic.framework.data.network.model.sets.toSets
import com.harismexis.magic.framework.data.network.retrofit.datasource.RetrofitDatasource
import com.harismexis.magic.framework.data.network.simple.NativeRemoteDatasource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private val retrofit: RetrofitDatasource,
    private val nativeRemote: NativeRemoteDatasource
) : MagicRemote {

    override suspend fun getCards(name: String?): List<Card> = retrofit.getCards(name).toCards()

    override fun getCardsBlocking(name: String?): List<Card> =
        nativeRemote.getCardsBlocking(name).toCards()

    override suspend fun getCardsMainSafe(name: String?): List<Card> =
        nativeRemote.getCardsMainSafe(name).toCards()

    override fun getSetsBlocking(name: String?): List<Set> =
        nativeRemote.getSetsBlocking(name).toSets()

    override suspend fun getSetsMainSafe(name: String?): List<Set> =
        nativeRemote.getSetsMainSafe(name).toSets()
}