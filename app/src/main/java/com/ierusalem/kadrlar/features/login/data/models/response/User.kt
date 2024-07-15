package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("profile")
    val profile: List<Profile>,
    @SerializedName("username")
    val username: String
)