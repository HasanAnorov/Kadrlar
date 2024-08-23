package com.ierusalem.kadrlar.features.profile.data.models.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: ProfileUser
)