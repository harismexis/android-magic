package com.harismexis.magic.framework.extensions.hero

import com.harismexis.magic.datamodel.domain.Card
import com.harismexis.magic.framework.datasource.network.model.RemoteHero
import com.harismexis.magic.framework.datasource.network.model.RemoteHeros

fun RemoteHeros?.toItems(): List<Card> {
    val items = mutableListOf<Card>()
    if (this == null) return items.toList()
    if(this.cards.isNullOrEmpty()) return items.toList()
    val filteredList = this.cards.filter { !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it !!.toItem(it.id !!)
    })
    return items.toList()
}

private fun RemoteHero.toItem(id: String): Card {
    return Card(
        id,
        this.name,
        this.type,
        this.imageUrl
    )
}
