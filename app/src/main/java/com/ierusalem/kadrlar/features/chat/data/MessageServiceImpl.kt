package com.ierusalem.kadrlar.features.chat.data

class MessageServiceImpl(
    private val client: MessageService
): MessageService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.getAllMessages()
        }catch (e: Exception){
            emptyList()
        }
    }
}