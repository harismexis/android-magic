package com.harismexis.magic.framework.datasource.network.data

import com.harismexis.magic.framework.datasource.network.api.MagicApi
import com.harismexis.magic.framework.datasource.network.model.RemoteCards
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MagicRemoteDatasource @Inject constructor(private val api: MagicApi) {

    suspend fun getCards(name: String? = null): RemoteCards? {
        return api.getCards(name)
    }

}