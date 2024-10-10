package com.ierusalem.kadrlar.core.app

import android.app.Application
import com.ierusalem.kadrlar.core.connectivity.ConnectivityObserver
import com.ierusalem.kadrlar.core.connectivity.NetworkConnectivityObserver
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.utils.FieldValidator
import com.ierusalem.kadrlar.features.chat.data.remote.ChatSocketService
import com.ierusalem.kadrlar.features.chat.data.remote.ChatSocketServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets
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

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging){
                level = LogLevel.ALL
            }
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }

}