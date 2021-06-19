package com.harismexis.magic.framework.di.application

import android.content.Context
import com.harismexis.magic.framework.datasource.database.RickAndMortyDatabase
import com.harismexis.magic.framework.datasource.database.data.RickAndMortyLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideLocalDao(@ApplicationContext app: Context): RickAndMortyLocalDao {
        return RickAndMortyDatabase.getDatabase(app).getDao()
    }

}