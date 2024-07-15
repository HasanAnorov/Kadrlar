package com.ierusalem.kadrlar.features.login.domain

import com.ierusalem.kadrlar.features.login.data.models.UserLoginRequest
import com.ierusalem.kadrlar.features.login.data.models.response.UserLoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun loginUser(userLoginRequest: UserLoginRequest): Response<UserLoginResponse>
}