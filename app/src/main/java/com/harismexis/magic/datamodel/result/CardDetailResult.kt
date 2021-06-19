package com.harismexis.magic.datamodel.result

import com.harismexis.magic.datamodel.domain.Card

sealed class CardDetailResult {
    data class Success(val item: Card): CardDetailResult()
    data class Error(val error: String): CardDetailResult()
}