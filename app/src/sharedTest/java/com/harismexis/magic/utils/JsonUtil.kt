package com.harismexis.magic.utils

import com.google.gson.Gson
import com.google.gson.JsonObject

inline fun <reified T> convertToModel(jsonString: String?): T {
    val gson = Gson()
    val json = gson.fromJson(jsonString, JsonObject::class.java)
    return Gson().fromJson(json, T::class.java)
}