package com.harismexis.magic.framework.di.application

import com.harismexis.magic.datamodel.repository.MagicLocal
import com.harismexis.magic.datamodel.repository.MagicRemote
import com.harismexis.magic.framework.datasource.database.repository.MagicLocalRepository
import com.harismexis.magic.framework.datasource.network.repository.MagicRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHeroLocal(
        localRepo: MagicLocalRepository
    ): MagicLocal

    @Binds
    abstract fun bindHeroRemote(
        remoteRepo: MagicRemoteRepository
    ): MagicRemote

}