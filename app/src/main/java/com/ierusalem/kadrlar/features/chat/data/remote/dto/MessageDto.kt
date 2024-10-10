package com.ierusalem.kadrlar.features.chat.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ierusalem.kadrlar.features.chat.domain.model.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val message:String,
    @SerializedName("user_id")
    val userId:Int
){
    fun toMessage(): Message {
        return Message(
            message = message,
            userId = userId
        )
    }
}

/**
 * Json Model
 * {
 *   "message": "Salom",
 *   "user_id": 1
 * }
 * */