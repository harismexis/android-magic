package com.harismexis.magic.framework.datasource.network.model

data class RemoteHeros(
    val info: RemoteInfo,
    val results: List<RemoteHero>,
)
