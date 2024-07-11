package com.ierusalem.kadrlar.core.app

import android.app.Application
import com.ierusalem.kadrlar.core.connectivity.ConnectivityObserver
import com.ierusalem.kadrlar.core.connectivity.NetworkConnectivityObserver
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.utils.FieldValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFieldValidator(): FieldValidator {
        return FieldValidator()
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(application: Application): ConnectivityObserver {
        return NetworkConnectivityObserver(context = application)
    }

    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStorePreferenceRepository {
        return DataStorePreferenceRepository(application)
    }

}