package com.example.laboratorio11.network.service


import com.codigotruko.ucahub.data.network.dto.login.LoginResponse
import com.codigotruko.ucahub.data.network.dto.register.RegisterRequest
import com.codigotruko.ucahub.data.network.dto.register.RegisterResponse
import com.codigotruko.ucahub.network.dto.login.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse

}