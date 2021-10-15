package com.harismexis.magic.framework.data.network.model.cards

import com.harismexis.magic.core.domain.Card

data class RemoteCards(
    val cards: List<RemoteCard>
)

fun RemoteCards?.toCards(): List<Card> {
    val items = mutableListOf<Card>()
    if (this == null) return items.toList()
    if (this.cards.isNullOrEmpty()) return items.toList()
    val filteredList = this.cards.filter { !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it.toCard(it.id!!)
    })
    return items.toList()
}

