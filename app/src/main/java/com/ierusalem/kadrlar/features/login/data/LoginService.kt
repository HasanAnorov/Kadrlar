package com.ierusalem.kadrlar.features.login.data

import com.ierusalem.kadrlar.features.login.data.models.UserLoginRequest
import com.ierusalem.kadrlar.features.login.data.models.response.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun loginUser(
        @Body body: UserLoginRequest
    ): Response<UserLoginResponse>

}