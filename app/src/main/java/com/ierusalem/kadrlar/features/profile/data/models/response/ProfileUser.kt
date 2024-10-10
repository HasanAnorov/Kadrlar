package com.ierusalem.kadrlar.features.profile.data.models.response


import com.google.gson.annotations.SerializedName

data class ProfileUser(
    @SerializedName("profile")
    val profile: ProfileData?,
    @SerializedName("username")
    val username: String
)