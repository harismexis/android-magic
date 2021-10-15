package com.harismexis.magic.framework.data.network.simple

import com.harismexis.magic.BuildConfig
import com.harismexis.magic.framework.data.network.model.cards.RemoteCards
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NativeRemoteDatasource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val httpHelper: HttpHelper
) {

    fun getCardsBlocking(
        name: String?
    ): RemoteCards {
        val url = URL(BuildConfig.MAGIC_API_BASE_URL + "cards?name=" + name)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            //return toCards(inputStream)
            return httpHelper.streamToModel(inputStream)
        }
        throw Exception("Cannot open HttpURLConnection")
    }

    suspend fun getCardsMainSafe(
        name: String?
    ): RemoteCards {
        return withContext(ioDispatcher) {
            getCardsBlocking(name)
        }
    }

//    private fun toCards(inStream: InputStream): RemoteCards {
//        val str = httpHelper.convertStreamToString(inStream)
//        return httpHelper.convertToModel(str)
//    }

}