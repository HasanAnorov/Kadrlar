package com.ierusalem.kadrlar.features.profile.data

import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") accessToken: String
    ): Response<ProfileResponse>

}