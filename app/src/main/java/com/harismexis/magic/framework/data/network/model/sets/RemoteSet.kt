package com.harismexis.magic.framework.data.network.model.sets

data class RemoteSet(
    val code: String, //"10E",
    val name: String, //"Tenth Edition",
    val type: String, // "core",
    val booster: List<String>,
    val releaseDate: String, //"2007-07-13",
    val block: String, //"Core Set",
    val onlineOnly: Boolean //false
)
