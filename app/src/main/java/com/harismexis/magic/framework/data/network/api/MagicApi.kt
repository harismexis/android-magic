package com.harismexis.magic.framework.data.network.api

import com.harismexis.magic.framework.data.network.model.RemoteCards
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicApi {

    @GET("cards")
    suspend fun getCards(
        @Query("name") name: String? = null
    ): RemoteCards?

}