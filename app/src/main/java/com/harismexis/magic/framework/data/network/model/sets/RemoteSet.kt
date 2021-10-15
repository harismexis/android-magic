package com.harismexis.magic.framework.data.network.model.sets

import com.harismexis.magic.core.domain.Set

data class RemoteSet(
    val code: String?, // "10E",
    val name: String?, // "Tenth Edition",
    val type: String?, // "core",
    val booster: List<String>?,
    val releaseDate: String?, // "2007-07-13",
    val block: String?, // "Core Set",
    val onlineOnly: Boolean? // false
)

fun RemoteSet.toSet(code: String): Set {
    return Set(
        code,
        this.name,
        this.type,
        this.booster,
        this.releaseDate,
        this.block,
        this.onlineOnly
    )
}
