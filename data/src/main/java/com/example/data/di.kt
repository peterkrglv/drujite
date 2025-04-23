package com.example.data

import com.example.data.api.UserApi
import com.example.data.test.CharacterReposirotyTest
import com.example.data.test.ClanRepositoryTest
import com.example.data.test.CustomisationRepositoryTest
import com.example.data.test.GoalRepositoryTest
import com.example.data.test.NewsRepositoryTest
import com.example.data.test.SessionRepositoryTest
import com.example.data.test.SharedPrefsRepositoryImpl
import com.example.domain.repos.CharacterRepository
import com.example.domain.repos.ClanRepository
import com.example.domain.repos.CustomisationRepository
import com.example.domain.repos.GoalRepository
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<OkHttpClient> { OkHttpClient() }
    single<Retrofit> { Retrofit.Builder()
        .baseUrl("https://drujite-server.onrender.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build()
    }
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<SessionRepository> { SessionRepositoryTest() }
    single<SharedPrefsRepository> { SharedPrefsRepositoryImpl(get()) }
    single<ClanRepository> { ClanRepositoryTest() }
    single<CharacterRepository> { CharacterReposirotyTest() }
    single<CustomisationRepository> { CustomisationRepositoryTest() }
    single<GoalRepository> { GoalRepositoryTest() }
    single<SessionRepository> { SessionRepositoryTest() }
    single<NewsRepository> { NewsRepositoryTest() }
}