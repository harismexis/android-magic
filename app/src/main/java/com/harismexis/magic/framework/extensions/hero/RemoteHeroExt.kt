package com.harismexis.magic.framework.extensions.hero

import com.harismexis.magic.datamodel.domain.Hero
import com.harismexis.magic.framework.datasource.network.model.RemoteHero
import com.harismexis.magic.framework.datasource.network.model.RemoteHeros

fun RemoteHeros?.toItems(): List<Hero> {
    val items = mutableListOf<Hero>()
    if (this == null) return items.toList()
    if(this.results.isNullOrEmpty()) return items.toList()
    val filteredList = this.results.filter { it.id != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.id !!)
    })
    return items.toList()
}

private fun RemoteHero.toItem(id: Int): Hero {
    return Hero(
        id,
        this.name,
        this.status,
        this.species,
        this.type,
        this.gender,
        this.image
    )
}
