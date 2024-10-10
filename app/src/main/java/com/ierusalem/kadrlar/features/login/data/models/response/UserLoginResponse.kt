package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("is_superuser")
    val isSuperuser: Boolean,
    @SerializedName("tokens")
    val tokens: Tokens,
    @SerializedName("username")
    val username: String
)