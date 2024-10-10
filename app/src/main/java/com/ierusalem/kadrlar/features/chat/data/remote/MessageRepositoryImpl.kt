package com.ierusalem.kadrlar.features.chat.data.remote

import com.ierusalem.kadrlar.features.chat.data.remote.response.ChatsResponse
import retrofit2.Response

class MessageRepositoryImpl(
    private val messageService: MessageService
) : MessagesRepository {

    override suspend fun getAllMessagesWithChatId(
        accessToken: String,
        body: ChatsRequestBody
    ): Response<ChatsResponse> {
        return messageService.getAllMessagesWithChatId(
            accessToken = accessToken,
            body = body
        )
    }
}