package com.harismexis.magic.framework.data.network.retrofit.datasource

import com.harismexis.magic.framework.data.network.model.cards.RemoteCards
import com.harismexis.magic.framework.data.network.retrofit.api.RetrofitApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDatasource @Inject constructor(private val api: RetrofitApi) {

    suspend fun getCards(name: String? = null): RemoteCards? {
        return api.getCards(name)
    }

}