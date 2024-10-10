package com.ierusalem.kadrlar.features.chat.data.remote.response


import com.google.gson.annotations.SerializedName
import com.ierusalem.kadrlar.features.chat.domain.model.Message

data class Message(
    @SerializedName("chat")
    val chat: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sender")
    val sender: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("timestamp")
    val timestamp: String
){
    fun toChatMessage(): Message {
        return Message(
            userId = sender,
            message = text
        )
    }
}