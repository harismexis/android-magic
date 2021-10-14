package com.harismexis.magic.framework.data.network.httpclient

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.network.model.RemoteCards
import com.harismexis.magic.framework.extensions.card.toItems
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpClientRepository @Inject constructor() : MagicRemote {

    companion object {
        const val BASE_URL = "https://api.magicthegathering.io/v1/"
    }

    // Not Used
    override suspend fun getCards(name: String?): List<Card> =
        listOf()

    override fun getCardsBlocking(
        name: String?
    ): List<Card> {
        val loginUrl = BASE_URL + "cards?name=" + name
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            return toCards(inputStream)
        }
        throw Exception("Cannot open HttpURLConnection")
    }

    private fun toCards(inStream: InputStream): List<Card> {
        val str = convertStreamToString(inStream)
        val model: RemoteCards = convertToModel(str)
        return model.toItems()
    }

    private fun convertStreamToString(inStream: InputStream): String? {
        val reader = BufferedReader(InputStreamReader(inStream))
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(
                    """
                    $line
                    
                    """.trimIndent()
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }

    inline fun <reified T> convertToModel(jsonString: String?): T {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, T::class.java)
    }

}