package com.harismexis.magic.framework.data.network.simple

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpHelper @Inject constructor(
) {

    fun convertStreamToString(inStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inStream))
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append("""$line""".trimIndent())
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