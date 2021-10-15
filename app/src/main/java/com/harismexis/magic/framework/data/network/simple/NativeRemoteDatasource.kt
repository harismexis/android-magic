package com.harismexis.magic.framework.data.network.simple

import com.harismexis.magic.BuildConfig
import com.harismexis.magic.core.domain.Magic
import com.harismexis.magic.framework.data.network.model.cards.RemoteCards
import com.harismexis.magic.framework.data.network.model.cards.toCards
import com.harismexis.magic.framework.data.network.model.sets.RemoteSets
import com.harismexis.magic.framework.data.network.model.sets.toSets
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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

    fun getSetsBlocking(
        name: String?
    ): RemoteSets {
        val url = URL(BuildConfig.MAGIC_API_BASE_URL + "sets")
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            return httpHelper.streamToModel(inputStream)
        }
        throw Exception("Cannot open HttpURLConnection")
    }

    suspend fun getSetsMainSafe(
        name: String?
    ): RemoteSets {
        return withContext(ioDispatcher) {
            getSetsBlocking(name)
        }
    }

    suspend fun getMagic(): Magic {
        // In parallel fetch cards and sets and return when both requests
        // complete and data is ready
        return coroutineScope {
            val cardsDeferred = async(ioDispatcher) {
                getCardsBlocking(null)
            }
            val setsDeferred = async(ioDispatcher) {
                getSetsBlocking(null)
            }
            val cards = cardsDeferred.await().toCards()
            val sets = setsDeferred.await().toSets()
            Magic(cards, sets)
        }
    }

}