package com.harismexis.magic.framework.data.network.datasource

import com.harismexis.magic.framework.data.network.api.MagicApi
import com.harismexis.magic.framework.data.network.model.RemoteCards
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MagicRemoteDatasource @Inject constructor(private val api: MagicApi) {

    suspend fun getCards(name: String? = null): RemoteCards? {
        return api.getCards(name)
    }

}