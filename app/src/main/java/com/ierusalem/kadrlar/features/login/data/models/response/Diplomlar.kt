package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class Diplomlar(
    @SerializedName("file")
    val `file`: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mutaxasisligi")
    val mutaxasisligi: String,
    @SerializedName("nomeri")
    val nomeri: String,
    @SerializedName("profile")
    val profile: Int,
    @SerializedName("seriyasi")
    val seriyasi: String,
    @SerializedName("tugatgan_muassasasi")
    val tugatganMuassasasi: String,
    @SerializedName("tugatgan_yili")
    val tugatganYili: String,
    @SerializedName("xujjat_turi")
    val xujjatTuri: String
)