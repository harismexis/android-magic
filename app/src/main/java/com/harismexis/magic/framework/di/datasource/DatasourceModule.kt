package com.harismexis.magic.framework.di.datasource

import android.content.Context
import com.harismexis.magic.framework.data.database.datasource.MagicLocalDao
import com.harismexis.magic.framework.data.database.db.MagicDatabase
import com.harismexis.magic.framework.data.network.retrofit.api.RetrofitApi
import com.harismexis.magic.framework.data.network.retrofit.datasource.RetrofitDatasource
import com.harismexis.magic.framework.data.network.simple.NativeRemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatasourceModule {

    @Provides
    @Singleton
    fun provideLocalDao(@ApplicationContext app: Context): MagicLocalDao {
        return MagicDatabase.getDatabase(app).getDao()
    }

    @Provides
    fun provideRetrofitDatasource(api: RetrofitApi): RetrofitDatasource {
        return RetrofitDatasource(api)
    }

    @Provides
    fun provideHttpClientDatasource(): NativeRemoteDatasource {
        return NativeRemoteDatasource(Dispatchers.IO)
    }

}