package com.harismexis.magic.framework.data.network.model.cards

import com.harismexis.magic.core.domain.Card

data class RemoteCard(
    val id: String?,
    val name: String?,
    val type: String?,
    val imageUrl: String?
)

fun RemoteCard.toCard(id: String): Card {
    return Card(
        id,
        this.name,
        this.type,
        this.imageUrl
    )
}
