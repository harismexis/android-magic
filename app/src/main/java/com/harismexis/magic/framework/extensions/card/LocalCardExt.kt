package com.harismexis.magic.framework.extensions.card

import com.harismexis.magic.core.domain.Card
import com.harismexis.magic.framework.data.database.table.LocalCard

fun List<LocalCard?>?.toItems(): List<Card> {
    val items = mutableListOf<Card>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalCard.toItem(): Card {
    return Card(
        this.id,
        this.name,
        this.type,
        this.imageUrl
    )
}

fun List<Card?>?.toLocalItems(): List<LocalCard> {
    val localItems = mutableListOf<LocalCard>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Card.toLocalItem(): LocalCard {
    return LocalCard(
        this.id,
        this.name,
        this.type,
        this.imageUrl
    )
}
