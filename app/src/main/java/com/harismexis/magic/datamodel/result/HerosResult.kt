package com.harismexis.magic.datamodel.result

import com.harismexis.magic.datamodel.domain.Hero

sealed class HerosResult {
    data class Success(val items: List<Hero>): HerosResult()
    data class Error(val error: Exception): HerosResult()
}