package com.example.drujite.ui

import com.example.data.SessionRepositoryTest
import com.example.data.SharedPrefsRepositoryImpl
import com.example.data.UserResitoryTest
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.GetSessionByCodeUseCase
import com.example.domain.use_cases.GetSessionsUseCase
import com.example.domain.use_cases.LoginUseCase
import com.example.domain.use_cases.SignupUseCase
import com.example.drujite.ui.greeting.GreetingViewModel
import com.example.drujite.ui.login.LoginViewModel
import com.example.drujite.ui.session_selection.SessionViewModel
import com.example.drujite.ui.signup.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserResitoryTest() }
    single<SessionRepository> { SessionRepositoryTest() }
    single<SharedPrefsRepository> { SharedPrefsRepositoryImpl(get()) }
}

val domainModule = module {
    factory<LoginUseCase> { LoginUseCase(get()) }
    factory<SignupUseCase> { SignupUseCase(get()) }
    factory<GetSessionsUseCase> { GetSessionsUseCase(get()) }
    factory<GetSessionByCodeUseCase> { GetSessionByCodeUseCase(get()) }
    factory<AccessSharedPrefsUseCase> { AccessSharedPrefsUseCase(get()) }
}

val appModule = module {
    viewModel<GreetingViewModel> { GreetingViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<SignupViewModel> { SignupViewModel(get()) }
    viewModel<SessionViewModel> { SessionViewModel(get(), get()) }
}