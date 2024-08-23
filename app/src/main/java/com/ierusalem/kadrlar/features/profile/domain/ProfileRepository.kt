package com.ierusalem.kadrlar.features.profile.domain

import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import retrofit2.Response

interface ProfileRepository {
    suspend fun getProfile(accessToken: String): Response<ProfileResponse>
}