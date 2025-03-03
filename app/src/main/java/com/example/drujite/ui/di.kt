package com.example.drujite.ui

import com.example.data.SessionRepositoryImpl
import com.example.data.UserResitoryImpl
import com.example.domain.GetSessionsUseCase
import com.example.domain.LoginUseCase
import com.example.domain.SessionRepository
import com.example.domain.SignupUseCase
import com.example.domain.UserRepository
import com.example.drujite.ui.greeting.GreetingViewModel
import com.example.drujite.ui.login.LoginViewModel
import com.example.drujite.ui.session_selection.SessionViewModel
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
    factory<GetSessionsUseCase> { GetSessionsUseCase(get()) }
}

val appModule = module {
    viewModel<GreetingViewModel> {GreetingViewModel()}
    viewModel<LoginViewModel> {LoginViewModel(get())}
    viewModel<SignupViewModel> {SignupViewModel(get())}
    viewModel<SessionViewModel> {SessionViewModel(get())}
}