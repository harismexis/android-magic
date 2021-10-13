package com.harismexis.magic.framework.di.application

import com.harismexis.magic.core.repository.MagicLocal
import com.harismexis.magic.core.repository.MagicRemote
import com.harismexis.magic.framework.data.database.repository.MagicLocalRepository
import com.harismexis.magic.framework.data.network.repository.MagicRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMagicLocal(
        localRepo: MagicLocalRepository
    ): MagicLocal

    @Binds
    abstract fun bindMagicRemote(
        remoteRepo: MagicRemoteRepository
    ): MagicRemote

}