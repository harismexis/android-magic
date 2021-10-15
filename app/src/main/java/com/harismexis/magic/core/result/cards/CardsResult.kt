package com.harismexis.magic.core.result.cards

import com.harismexis.magic.core.domain.Card

sealed class CardsResult {
    data class Success(val items: List<Card>): CardsResult()
    data class Error(val error: Exception): CardsResult()
}