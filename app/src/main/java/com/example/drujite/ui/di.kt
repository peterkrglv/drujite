package com.example.drujite.ui

import com.example.drujite.data.SessionRepositoryImpl
import com.example.drujite.data.UserResitoryImpl
import com.example.drujite.domain.LoginUseCase
import com.example.drujite.domain.SessionRepository
import com.example.drujite.domain.SignupUseCase
import com.example.drujite.domain.UserRepository
import com.example.drujite.ui.greeting.GreetingViewModel
import com.example.drujite.ui.login.LoginViewModel
import com.example.drujite.ui.signup.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserResitoryImpl() }
    single<SessionRepository> { SessionRepositoryImpl() }
}

val domainModule = module {
    factory<LoginUseCase> { LoginUseCase(get()) }
    factory<SignupUseCase> { SignupUseCase(get()) }
}

val appModule = module {
    viewModel<GreetingViewModel> {GreetingViewModel()}
    viewModel<LoginViewModel> {LoginViewModel(get())}
    viewModel<SignupViewModel> {SignupViewModel(get())}
}