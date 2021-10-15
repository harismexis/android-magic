package com.harismexis.magic.core.result.sets

import com.harismexis.magic.core.domain.Set

sealed class SetsResult {
    data class Success(val items: List<Set>) : SetsResult()
    data class Error(val error: Exception) : SetsResult()
}