package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("user")
    val user: User
)