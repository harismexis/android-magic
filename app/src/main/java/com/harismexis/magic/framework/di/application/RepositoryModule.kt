package com.harismexis.magic.framework.di.application

import com.harismexis.magic.datamodel.repository.HeroLocal
import com.harismexis.magic.datamodel.repository.HeroRemote
import com.harismexis.magic.framework.datasource.database.repository.HeroLocalRepository
import com.harismexis.magic.framework.datasource.network.repository.HeroRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHeroLocal(
        localRepo: HeroLocalRepository
    ): HeroLocal

    @Binds
    abstract fun bindHeroRemote(
        remoteRepo: HeroRemoteRepository
    ): HeroRemote

}