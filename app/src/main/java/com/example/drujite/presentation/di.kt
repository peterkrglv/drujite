package com.example.drujite.presentation

import com.example.data.CharacterReposirotyTest
import com.example.data.ClanRepositoryTest
import com.example.data.CustomisationRepositoryTest
import com.example.data.GoalRepositoryTest
import com.example.data.SessionRepositoryTest
import com.example.data.SharedPrefsRepositoryImpl
import com.example.data.UserResitoryTest
import com.example.domain.repos.CharacterRepository
import com.example.domain.repos.ClanRepository
import com.example.domain.repos.CustomisationRepository
import com.example.domain.repos.GoalRepository
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.AddCharacterToSessionUseCase
import com.example.domain.use_cases.CreateCharacterUseCase
import com.example.domain.use_cases.GetCharacterByIdUseCase
import com.example.domain.use_cases.GetCharactersByUserIdUseCase
import com.example.domain.use_cases.GetClansBySessionIdUseCase
import com.example.domain.use_cases.GetCurrentUser
import com.example.domain.use_cases.GetCustomisationOptions
import com.example.domain.use_cases.GetGoalsByCharacterIdUseCase
import com.example.domain.use_cases.GetSessionByCodeUseCase
import com.example.domain.use_cases.GetSessionsNewsUseCase
import com.example.domain.use_cases.GetSessionsOfUserUseCase
import com.example.domain.use_cases.GetSessionsUseCase
import com.example.domain.use_cases.LogOutUseCase
import com.example.domain.use_cases.LoginUseCase
import com.example.domain.use_cases.SaveCharacterCustomImageUseCase
import com.example.domain.use_cases.SignupUseCase
import com.example.domain.use_cases.UpdateGoalStatusUseCase
import com.example.drujite.presentation.character_creation.CreationViewModel
import com.example.drujite.presentation.character_customisation.CustomisationViewModel
import com.example.drujite.presentation.character_transfer.TransferViewModel
import com.example.drujite.presentation.greeting.GreetingViewModel
import com.example.drujite.presentation.home.HomeViewModel
import com.example.drujite.presentation.login.LoginViewModel
import com.example.drujite.presentation.profile.ProfileViewModel
import com.example.drujite.presentation.session_news.NewsViewModel
import com.example.drujite.presentation.session_selection.SessionViewModel
import com.example.drujite.presentation.signup.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserResitoryTest() }
    single<SessionRepository> { SessionRepositoryTest() }
    single<SharedPrefsRepository> { SharedPrefsRepositoryImpl(get()) }
    single<ClanRepository> { ClanRepositoryTest() }
    single<CharacterRepository> { CharacterReposirotyTest() }
    single<CustomisationRepository> { CustomisationRepositoryTest() }
    single<GoalRepository> { GoalRepositoryTest() }
    single<SessionRepository> { SessionRepositoryTest() }
}

val domainModule = module {
    factory<LoginUseCase> { LoginUseCase(get(), get()) }
    factory<SignupUseCase> { SignupUseCase(get(), get()) }
    factory<GetSessionsUseCase> { GetSessionsUseCase(get()) }
    factory<GetSessionByCodeUseCase> { GetSessionByCodeUseCase(get()) }
    factory<AccessSharedPrefsUseCase> { AccessSharedPrefsUseCase(get()) }
    factory<GetClansBySessionIdUseCase> { GetClansBySessionIdUseCase(get()) }
    factory<CreateCharacterUseCase> { CreateCharacterUseCase(get()) }
    factory<GetCharactersByUserIdUseCase> { GetCharactersByUserIdUseCase(get()) }
    factory<AddCharacterToSessionUseCase> { AddCharacterToSessionUseCase(get()) }
    factory<GetCustomisationOptions> { GetCustomisationOptions(get()) }
    factory<SaveCharacterCustomImageUseCase> { SaveCharacterCustomImageUseCase( get() ) }
    factory<GetCharacterByIdUseCase> { GetCharacterByIdUseCase(get()) }
    factory<GetGoalsByCharacterIdUseCase> { GetGoalsByCharacterIdUseCase(get()) }
    factory<UpdateGoalStatusUseCase> { UpdateGoalStatusUseCase(get()) }
    factory<LogOutUseCase> { LogOutUseCase(get()) }
    factory<GetSessionsOfUserUseCase> { GetSessionsOfUserUseCase(get()) }
    factory<GetCurrentUser> { GetCurrentUser(get(), get()) }
    factory<GetSessionsNewsUseCase> { GetSessionsNewsUseCase(get(), get()) }
}

val appModule = module {
    viewModel<GreetingViewModel> { GreetingViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<SignupViewModel> { SignupViewModel(get()) }
    viewModel<SessionViewModel> { SessionViewModel(get(), get(), get()) }
    viewModel<CreationViewModel> { CreationViewModel(get(), get(), get()) }
    viewModel<TransferViewModel> { TransferViewModel(get(), get(), get()) }
    viewModel<CustomisationViewModel> { CustomisationViewModel(get(), get()) }
    viewModel<HomeViewModel> { HomeViewModel(get(), get(), get(), get()) }
    viewModel<ProfileViewModel> { ProfileViewModel(get(), get(), get(), get()) }
    viewModel<NewsViewModel> { NewsViewModel(get()) }
}