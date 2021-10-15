package com.harismexis.magic.framework.data.network.model.sets

import com.harismexis.magic.core.domain.Set

data class RemoteSets(
    val sets: List<RemoteSet>
)

fun RemoteSets?.toSets(): List<Set> {
    val items = mutableListOf<Set>()
    if (this == null) return items.toList()
    if (this.sets.isNullOrEmpty()) return items.toList()
    val filteredList = this.sets.filter { !it.code.isNullOrBlank() }
    items.addAll(filteredList.map {
        it.toSet(it.code!!)
    })
    return items.toList()
}

