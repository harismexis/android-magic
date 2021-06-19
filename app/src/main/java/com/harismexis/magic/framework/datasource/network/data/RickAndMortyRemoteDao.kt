package com.harismexis.magic.framework.datasource.network.data

import com.harismexis.magic.framework.datasource.network.api.RickAndMortyApi
import com.harismexis.magic.framework.datasource.network.model.RemoteHeros
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRemoteDao @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getHeros(name: String? = null): RemoteHeros? {
        return api.getHeros(name)
    }

}