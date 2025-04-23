//package com.example.data
//
//import android.util.Log
//import com.example.domain.models.UserModel
//import com.example.domain.repos.UserRepository
//import com.example.domain.use_cases.user.LoginResponse
//import com.example.domain.use_cases.user.LoginResult
//import com.example.domain.use_cases.user.SignupResponse
//import com.example.domain.use_cases.user.SignupResult
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.RequestBody.Companion.toRequestBody
//import org.json.JSONObject
//
//class UserRepositoryImpl(
//    private val client: OkHttpClient
//) : UserRepository {
//    private val url = "https://drujite-server.onrender.com/api/v1/"
//
//    override suspend fun login(phone: String, password: String): LoginResponse {
//        val mediaType = "application/json".toMediaType()
//        val jsonBody = JSONObject().apply {
//            put("phone", phone)
//            put("password", password)
//        }
//        val requestBody = jsonBody.toString().toRequestBody(mediaType)
//        val request = Request.Builder().url(url + "auth").post(requestBody).build()
//
//        return try {
//            val response = client.newCall(request).execute()
//            Log.d("server", response.toString())
//            when (response.code) {
//                200 -> {
//                    val responseBody = response.body?.string()
//                    val token = JSONObject(responseBody ?: "").getString("token")
//                    LoginResponse(LoginResult.SUCCESS, token = token).also {
//                        Log.d("server", "Login successful: $token")
//                    }
//                }
//                401 -> LoginResponse(LoginResult.WRONG_PASSWORD)
//                404 -> LoginResponse(LoginResult.WRONG_PHONE)
//                else -> LoginResponse(LoginResult.NETWORK_ERROR)
//            }
//        } catch (e: Exception) {
//            LoginResponse(LoginResult.NETWORK_ERROR)
//        }
//    }
//
//
//    override suspend fun signup(
//        name: String, phone: String, password: String, gender: String
//    ): SignupResponse {
//        val mediaType = "application/json".toMediaType()
//        val jsonBody = JSONObject().apply {
//            put("username", name)
//            put("phone", phone)
//            put("password", password)
//            put("gender", gender)
//        }
//        val requestBody = jsonBody.toString().toRequestBody(mediaType)
//        val request = Request.Builder().url(url + "signup").post(requestBody).build()
//
//        return try {
//            val response = client.newCall(request).execute()
//            Log.d("server", response.toString())
//            when (response.code) {
//                200 -> {
//                    val responseBody = response.body?.string()
//                    val token = JSONObject(responseBody ?: "").getString("token")
//                    SignupResponse(SignupResult.SUCCESS, token = token).also {
//                        Log.d("server", "Signup successful: $token")
//                    }
//                }
//
//                409 -> SignupResponse(SignupResult.PHONE_ALREADY_REGISTERED)
//                else -> SignupResponse(SignupResult.NETWORK_ERROR)
//            }
//        } catch (e: Exception) {
//            SignupResponse(SignupResult.NETWORK_ERROR)
//        }
//    }
//
//    override suspend fun getUserByToken(userToken: String): UserModel {
//        return UserModel(
//            token = "1", name = "Анастасия Чеботарь", phone = "+1234567890"
//        )
//    }
//}


package com.example.data

import android.util.Log
import com.example.data.api.UserApi
import com.example.data.requests.LoginRequest
import com.example.data.requests.SignupRequest
import com.example.data.responces.TokenResponse
import com.example.domain.models.UserModel
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository
import com.example.domain.use_cases.user.LoginResponse
import com.example.domain.use_cases.user.LoginResult
import com.example.domain.use_cases.user.SignupResponse
import com.example.domain.use_cases.user.SignupResult
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val sharedPrefs: SharedPrefsRepository
) : UserRepository {

    override suspend fun login(phone: String, password: String): LoginResponse {
        Log.d("server", "Login attempt with phone: $phone and password: $password")
        return try {
            val response: TokenResponse = userApi.login(LoginRequest(phone, password))
            Log.d("server", "Login successful: ${response.token}")
            LoginResponse(LoginResult.SUCCESS, token = response.token)
        } catch (e: HttpException) {
            Log.e("server", "Login failed:${e.code()} ${e.message()}")
            when (e.code()) {
                401 -> LoginResponse(LoginResult.WRONG_PASSWORD)
                404 -> LoginResponse(LoginResult.WRONG_PHONE)
                else -> LoginResponse(LoginResult.NETWORK_ERROR)
            }
        } catch (e: IOException) {
            Log.e("server", "Network error: ${e.message}")
            LoginResponse(LoginResult.NETWORK_ERROR)
        }
    }

    override suspend fun signup(
        name: String, phone: String, password: String, gender: String
    ): SignupResponse {
        Log.d("server", "Signup attempt with phone: $phone and password: $password")
        return try {
            val response: TokenResponse =
                userApi.signup(SignupRequest(name, phone, password, gender))
            Log.d("server", "Signup successful: ${response.token}")
            SignupResponse(SignupResult.SUCCESS, token = response.token)
        } catch (e: HttpException) {
            Log.e("server", "Signup failed:${e.code()} ${e.message()}")
            when (e.code()) {
                409 -> SignupResponse(SignupResult.PHONE_ALREADY_REGISTERED)
                else -> SignupResponse(SignupResult.NETWORK_ERROR)
            }
        } catch (e: IOException) {
            Log.e("server", "Network error: ${e.message}")
            SignupResponse(SignupResult.NETWORK_ERROR)
        }
    }

    override suspend fun getUserByToken(userToken: String): UserModel? {
        return try {
            val token = sharedPrefs.getUserToken() ?: return null
            Log.d("server", "Fetching user with token: $token")
            val response = userApi.getUserByToken("Bearer $token")
            Log.d("server", "User fetched successfully: ${response.username}")
            UserModel(
                token = token,
                name = response.username,
                phone = response.phone
            )
        } catch (e: HttpException) {
            Log.d("server", "Failed to fetch user: ${e.code()} ${e.message()}")
            null
        } catch (e: IOException) {
            Log.d("server", "Network error: ${e.message}")
            null
        }
    }
}