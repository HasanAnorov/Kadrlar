package com.ierusalem.kadrlar.features.chat.data.remote

import com.ierusalem.kadrlar.features.chat.data.remote.response.ChatsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MessageService {

    @POST("chats/")
    suspend fun getAllMessagesWithChatId(
        @Header("Authorization") accessToken: String,
        @Body body: ChatsRequestBody
    ): Response<ChatsResponse>

}

data class ChatsRequestBody(
    val user:Int,
    val admin:Int
)
