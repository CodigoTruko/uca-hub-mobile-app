package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.dto.login.LoginRequest
import com.codigotruko.ucahub.data.network.dto.login.LoginResponse
import com.codigotruko.ucahub.data.network.dto.register.RegisterRequest
import com.codigotruko.ucahub.data.network.dto.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/v2/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("auth/v2/register")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse

}