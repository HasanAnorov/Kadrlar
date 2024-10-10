package com.ierusalem.kadrlar.features.chat.data.remote

import com.ierusalem.kadrlar.features.chat.data.remote.response.ChatsResponse
import retrofit2.Response

interface MessagesRepository {

    suspend fun getAllMessagesWithChatId(
        accessToken: String,
        body: ChatsRequestBody
    ): Response<ChatsResponse>

}