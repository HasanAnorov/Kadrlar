package com.ierusalem.kadrlar.features.login.data

import com.ierusalem.kadrlar.features.login.data.models.UserLoginRequest
import com.ierusalem.kadrlar.features.login.data.models.response.UserLoginResponse
import com.ierusalem.kadrlar.features.login.domain.LoginRepository
import retrofit2.Response

class LoginRepositoryImpl(
    private val service: LoginService
) : LoginRepository {

    override suspend fun loginUser(userLoginRequest: UserLoginRequest): Response<UserLoginResponse> {
        return service.loginUser(userLoginRequest)
    }

}