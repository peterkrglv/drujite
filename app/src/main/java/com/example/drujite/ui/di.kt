package com.example.drujite.ui

import com.example.drujite.ui.greeting.GreetingViewModel
import com.example.drujite.ui.login.LoginViewModel
import com.example.drujite.ui.signup.SignupViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

}

val domainModule = module {

}

val appModule = module {
    viewModel<GreetingViewModel> {GreetingViewModel()}
    viewModel<LoginViewModel> {LoginViewModel()}
    viewModel<SignupViewModel> {SignupViewModel()}
}