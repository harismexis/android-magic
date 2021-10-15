package com.harismexis.magic.core.domain

data class Set(
    val code: String,
    val name: String?,
    val type: String?,
    val booster: List<String>?,
    val releaseDate: String?,
    val block: String?,
    val onlineOnly: Boolean?
)
