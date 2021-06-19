package com.harismexis.magic.presentation.result

import com.harismexis.magic.datamodel.domain.Hero

sealed class HeroDetailResult {
    data class Success(val item: Hero): HeroDetailResult()
    data class Error(val error: String): HeroDetailResult()
}