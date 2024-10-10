package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class Tokens(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String
)