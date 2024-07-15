package com.ierusalem.kadrlar.features.login.data.models.response


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("address")
    val address: Any,
    @SerializedName("boshlagan_vaqti")
    val boshlaganVaqti: Any,
    @SerializedName("citizenship")
    val citizenship: String,
    @SerializedName("city")
    val city: Any,
    @SerializedName("date_of_birth")
    val dateOfBirth: Any,
    @SerializedName("date_of_passport")
    val dateOfPassport: Any,
    @SerializedName("davlat_mukofotlari")
    val davlatMukofotlari: Any,
    @SerializedName("diplomlar")
    val diplomlar: List<Diplomlar>,
    @SerializedName("district")
    val district: Any,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("idoraviy_mukofotlari")
    val idoraviyMukofotlari: Any,
    @SerializedName("inn_nomer")
    val innNomer: Any,
    @SerializedName("jshir")
    val jshir: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("Malaka_tashkilot_nomi")
    val malakaTashkilotNomi: Any,
    @SerializedName("malaka_yunalish")
    val malakaYunalish: Any,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("obyektivka")
    val obyektivka: Any,
    @SerializedName("partisanship")
    val partisanship: String,
    @SerializedName("pasport_nomer")
    val pasportNomer: String,
    @SerializedName("pasport_pdf")
    val pasportPdf: Any,
    @SerializedName("pasport_seria")
    val pasportSeria: String,
    @SerializedName("passport_issued")
    val passportIssued: String,
    @SerializedName("patronymic_name")
    val patronymicName: String,
    @SerializedName("pensiya_daftari_raqimi")
    val pensiyaDaftariRaqimi: Any,
    @SerializedName("permanent_Address")
    val permanentAddress: Any,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tilini_bilish_daraja")
    val tiliniBilishDaraja: Any,
    @SerializedName("tugatgan_vaqti")
    val tugatganVaqti: Any
)