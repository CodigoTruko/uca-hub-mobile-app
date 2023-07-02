package com.codigotruko.ucahub.repository


import com.codigotruko.ucahub.data.network.ApiResponse
import com.codigotruko.ucahub.data.network.dto.login.LoginRequest
import com.codigotruko.ucahub.data.network.dto.register.RegisterRequest
import com.codigotruko.ucahub.data.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class CredentialsRepository(private val api: AuthService) {

    suspend fun login(email: String, password: String): ApiResponse<String>{
        try {
            val response = api.login(LoginRequest(email, password))
            return ApiResponse.Success(response.token)
        }
        catch (e: HttpException){
            if (e.code() == 400){
                return ApiResponse.ErrorWithMessage("Invalid email or password")
            }
            return ApiResponse.Error(e)
        }
        catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }


    suspend fun register(name: String, carnet: String, username: String, email: String, password: String): ApiResponse<String>{
        try {
            val response = api.register(RegisterRequest(name, carnet, username, email, password))
            return ApiResponse.Success(response.message)
        }
        catch (e: HttpException){
            if(e.code() == 401){
                return ApiResponse.ErrorWithMessage("Credentials conflict")
            }
            return ApiResponse.Error(e)
        }
        catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

}