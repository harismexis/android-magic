package com.harismexis.magic.framework.extensions.card

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.framework.data.network.model.RemoteCard
import com.harismexis.magic.framework.data.network.model.RemoteCards

fun RemoteCards?.toItems(): List<Card> {
    val items = mutableListOf<Card>()
    if (this == null) return items.toList()
    if(this.cards.isNullOrEmpty()) return items.toList()
    val filteredList = this.cards.filter { !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it !!.toItem(it.id !!)
    })
    return items.toList()
}

private fun RemoteCard.toItem(id: String): Card {
    return Card(
        id,
        this.name,
        this.type,
        this.imageUrl
    )
}
