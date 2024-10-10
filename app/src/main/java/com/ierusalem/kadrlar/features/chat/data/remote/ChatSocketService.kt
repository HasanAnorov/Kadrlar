package com.ierusalem.kadrlar.features.chat.data.remote

import com.ierusalem.kadrlar.core.utils.Resource
import com.ierusalem.kadrlar.features.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        userId: Int
    ): Resource<Unit>

    suspend fun sendMessages(message:String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object{
        const val BASE_URL = "ws://213.230.126.222:9190/"
//        const val BASE_URL = "ws://172.16.0.13:7777/"
    }

    sealed class Endpoints(val url:String){
//        data object ChatSocket: Endpoints("$BASE_URL/ws/chat/")
        data object ChatSocket: Endpoints("$BASE_URL/ws/chat/1/")
    }

}