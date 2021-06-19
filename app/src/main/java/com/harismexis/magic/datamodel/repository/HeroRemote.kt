package com.harismexis.magic.datamodel.repository

import com.harismexis.magic.datamodel.domain.Hero

interface HeroRemote {
    suspend fun getHeros(name: String? = null): List<Hero>

}