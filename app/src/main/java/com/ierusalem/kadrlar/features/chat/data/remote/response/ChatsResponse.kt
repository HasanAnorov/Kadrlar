package com.ierusalem.kadrlar.features.chat.data.remote.response


import com.google.gson.annotations.SerializedName

data class ChatsResponse(
    @SerializedName("admin")
    val admin: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("user")
    val user: Int
)