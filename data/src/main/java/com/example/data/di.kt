package com.example.data

import com.example.data.api.CharacterApi
import com.example.data.api.ClanApi
import com.example.data.api.GoalApi
import com.example.data.api.NewsApi
import com.example.data.api.SessionApi
import com.example.data.api.UserApi
import com.example.data.repos.CharacterRepositoryImpl
import com.example.data.repos.ClanRepositoryImpl
import com.example.data.repos.GoalRepositoryImpl
import com.example.data.repos.NewsRepositoryImpl
import com.example.data.repos.SessionRepositoryImpl
import com.example.data.repos.SharedPrefsRepositoryImpl
import com.example.data.repos.UserRepositoryImpl
import com.example.data.`test-repos`.CustomisationRepositoryTest
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
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://drujite-server.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<CharacterApi> { get<Retrofit>().create(CharacterApi::class.java) }
    single<ClanApi> { get<Retrofit>().create(ClanApi::class.java) }
    single<GoalApi> { get<Retrofit>().create(GoalApi::class.java) }
    single<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
    single<SessionApi> { get<Retrofit>().create(SessionApi::class.java) }
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    single<ClanRepository> { ClanRepositoryImpl(get(), get()) }
    single<GoalRepository> { GoalRepositoryImpl(get(), get()) }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    single<SessionRepository> { SessionRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<SharedPrefsRepository> { SharedPrefsRepositoryImpl(get()) }
    single<CustomisationRepository> { CustomisationRepositoryTest() }
}