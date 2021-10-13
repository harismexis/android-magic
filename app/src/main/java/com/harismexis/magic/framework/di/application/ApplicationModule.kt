package com.harismexis.magic.framework.di.application

import android.content.Context
import com.harismexis.magic.framework.data.database.datasource.MagicLocalDao
import com.harismexis.magic.framework.data.database.db.MagicDatabase
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
    fun provideLocalDao(@ApplicationContext app: Context): MagicLocalDao {
        return MagicDatabase.getDatabase(app).getDao()
    }

}