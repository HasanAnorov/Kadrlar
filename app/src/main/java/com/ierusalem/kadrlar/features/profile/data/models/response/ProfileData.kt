package com.ierusalem.kadrlar.features.profile.data.models.response


import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("address")
    val address: String?,
    @SerializedName("boshlagan_vaqti")
    val boshlaganVaqti: String?,
    @SerializedName("citizenship")
    val citizenship: String,
    @SerializedName("city")
    val city: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    @SerializedName("date_of_passport")
    val dateOfPassport: String?,
    @SerializedName("davlat_mukofotlari")
    val davlatMukofotlari: String?,
    @SerializedName("diplomlar")
    val diplomlar: List<Diplomlar>,
    @SerializedName("district")
    val district: String?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("idoraviy_mukofotlari")
    val idoraviyMukofotlari: String?,
    @SerializedName("inn_nomer")
    val innNomer: String?,
    @SerializedName("jshir")
    val jshir: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("Malaka_tashkilot_nomi")
    val malakaTashkilotNomi: String?,
    @SerializedName("malaka_yunalish")
    val malakaYunalish: String?,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("obyektivka")
    val obyektivka: String?,
    @SerializedName("partisanship")
    val partisanship: String,
    @SerializedName("pasport_nomer")
    val pasportNomer: String,
    @SerializedName("pasport_pdf")
    val pasportPdf: String?,
    @SerializedName("pasport_seria")
    val pasportSeria: String,
    @SerializedName("passport_issued")
    val passportIssued: String,
    @SerializedName("patronymic_name")
    val patronymicName: String,
    @SerializedName("pensiya_daftari_raqimi")
    val pensiyaDaftariRaqimi: String?,
    @SerializedName("permanent_Address")
    val permanentAddress: String?,
    @SerializedName("phone_no")
    val phoneNo: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("tilini_bilish_daraja")
    val tiliniBilishDaraja: String?,
    @SerializedName("tugatgan_vaqti")
    val tugatganVaqti: String?
)