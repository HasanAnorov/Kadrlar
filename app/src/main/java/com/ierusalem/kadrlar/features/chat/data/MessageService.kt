package com.ierusalem.kadrlar.features.chat.data

import retrofit2.http.GET

interface MessageService {

    @GET("chats")
    suspend fun getAllMessages(): List<Message>

}

data class Message(
    val text: String,
)