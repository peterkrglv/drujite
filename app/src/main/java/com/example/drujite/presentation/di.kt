package com.example.drujite.presentation

import com.example.data.dataModule
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.character.AddCharacterToSessionUseCase
import com.example.domain.use_cases.character.CreateCharacterUseCase
import com.example.domain.use_cases.character.GetCharacterByIdUseCase
import com.example.domain.use_cases.character.GetCharacterBySessionIdUseCase
import com.example.domain.use_cases.character.GetCharactersBySessionIdUseCase
import com.example.domain.use_cases.character.GetCustomisationOptions
import com.example.domain.use_cases.character.GetUsersCharactersUseCase
import com.example.domain.use_cases.character.SaveCharacterCustomImageUseCase
import com.example.domain.use_cases.goal.GetGoalsByCharacterIdUseCase
import com.example.domain.use_cases.goal.UpdateGoalStatusUseCase
import com.example.domain.use_cases.session.GetClansBySessionIdUseCase
import com.example.domain.use_cases.session.GetSessionByCodeUseCase
import com.example.domain.use_cases.session.GetSessionsNewsUseCase
import com.example.domain.use_cases.session.GetTimeTableUseCase
import com.example.domain.use_cases.session.GetUsersSessionsUseCase
import com.example.domain.use_cases.user.GetCurrentUser
import com.example.domain.use_cases.user.LogOutUseCase
import com.example.domain.use_cases.user.LoginUseCase
import com.example.domain.use_cases.user.SignupUseCase
import com.example.drujite.presentation.character_creation.CreationViewModel
import com.example.drujite.presentation.character_customisation.CustomisationViewModel
import com.example.drujite.presentation.character_transfer.TransferViewModel
import com.example.drujite.presentation.greeting.GreetingViewModel
import com.example.drujite.presentation.home.HomeViewModel
import com.example.drujite.presentation.login.LoginViewModel
import com.example.drujite.presentation.other_characters.OtherCharactersViewModel
import com.example.drujite.presentation.profile.ProfileViewModel
import com.example.drujite.presentation.session_news.NewsViewModel
import com.example.drujite.presentation.session_selection.SessionViewModel
import com.example.drujite.presentation.signup.SignupViewModel
import com.example.drujite.presentation.timetable.TimetableViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val domainModule = module {
    factory<LoginUseCase> { LoginUseCase(get(), get()) }
    factory<SignupUseCase> { SignupUseCase(get(), get()) }
    factory<GetUsersSessionsUseCase> { GetUsersSessionsUseCase(get()) }
    factory<GetSessionByCodeUseCase> { GetSessionByCodeUseCase(get()) }
    factory<AccessSharedPrefsUseCase> { AccessSharedPrefsUseCase(get()) }
    factory<GetClansBySessionIdUseCase> { GetClansBySessionIdUseCase(get()) }
    factory<CreateCharacterUseCase> { CreateCharacterUseCase(get(), get(), get()) }
    factory<GetUsersCharactersUseCase> { GetUsersCharactersUseCase(get()) }
    factory<AddCharacterToSessionUseCase> { AddCharacterToSessionUseCase(get()) }
    factory<GetCustomisationOptions> { GetCustomisationOptions(get()) }
    factory<SaveCharacterCustomImageUseCase> { SaveCharacterCustomImageUseCase(get()) }
    factory<GetCharacterByIdUseCase> { GetCharacterByIdUseCase(get()) }
    factory<GetGoalsByCharacterIdUseCase> { GetGoalsByCharacterIdUseCase(get()) }
    factory<UpdateGoalStatusUseCase> { UpdateGoalStatusUseCase(get()) }
    factory<LogOutUseCase> { LogOutUseCase(get()) }
    factory<GetCurrentUser> { GetCurrentUser(get(), get()) }
    factory<GetSessionsNewsUseCase> { GetSessionsNewsUseCase(get(), get()) }
    factory<GetCharactersBySessionIdUseCase> { GetCharactersBySessionIdUseCase(get()) }
    factory<GetTimeTableUseCase> { GetTimeTableUseCase(get(), get()) }
    factory<SaveCharacterCustomImageUseCase> { SaveCharacterCustomImageUseCase(get()) }
    factory<GetCharacterBySessionIdUseCase> { GetCharacterBySessionIdUseCase(get()) }
}

val appModule = module {
    viewModel<GreetingViewModel> { GreetingViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<SignupViewModel> { SignupViewModel(get()) }
    viewModel<SessionViewModel> { SessionViewModel(get(), get(), get(), get()) }
    viewModel<CreationViewModel> { CreationViewModel(get(), get()) }
    viewModel<TransferViewModel> { TransferViewModel(get(), get(), get()) }
    viewModel<CustomisationViewModel> { CustomisationViewModel(get(), get()) }
    viewModel<HomeViewModel> { HomeViewModel(get(), get(), get(), get()) }
    viewModel<ProfileViewModel> { ProfileViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel<NewsViewModel> { NewsViewModel(get()) }
    viewModel<OtherCharactersViewModel> { OtherCharactersViewModel(get(), get()) }
    viewModel<TimetableViewModel> { TimetableViewModel(get()) }
}

val koinModules = listOf(appModule, domainModule, dataModule)