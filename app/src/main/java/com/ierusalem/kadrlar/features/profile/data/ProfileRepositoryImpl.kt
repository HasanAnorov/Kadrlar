package com.ierusalem.kadrlar.features.profile.data

import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import com.ierusalem.kadrlar.features.profile.domain.ProfileRepository
import retrofit2.Response

class ProfileRepositoryImpl(private val profileService: ProfileService) : ProfileRepository {
    override suspend fun getProfile(accessToken: String): Response<ProfileResponse> {
        return profileService.getProfile(accessToken)
    }
}