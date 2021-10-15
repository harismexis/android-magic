package com.harismexis.magic.core.result.cards

import com.harismexis.magic.core.domain.Card

sealed class CardDetailResult {
    data class Success(val item: Card): CardDetailResult()
    data class Error(val error: String): CardDetailResult()
}