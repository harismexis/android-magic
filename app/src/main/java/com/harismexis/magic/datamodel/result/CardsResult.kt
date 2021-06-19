package com.harismexis.magic.datamodel.result

import com.harismexis.magic.datamodel.domain.Card

sealed class CardsResult {
    data class Success(val items: List<Card>): CardsResult()
    data class Error(val error: Exception): CardsResult()
}