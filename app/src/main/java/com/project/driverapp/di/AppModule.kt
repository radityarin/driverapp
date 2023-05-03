package com.project.driverapp.di

import com.project.driverapp.data.source.AppRepositoryImpl
import com.project.driverapp.data.source.remote.RemoteDataSource
import com.project.driverapp.domain.repositories.AppRepository
import com.project.driverapp.domain.usecases.auth.AuthInteractor
import com.project.driverapp.domain.usecases.auth.AuthUseCase
import com.project.driverapp.domain.usecases.questioner.QuestionerInteractor
import com.project.driverapp.domain.usecases.questioner.QuestionerUseCase
import com.project.driverapp.presentation.home.users.ui.account.AccountViewModel
import com.project.driverapp.presentation.home.users.ui.history.HistoryViewModel
import com.project.driverapp.presentation.login.LoginViewModel
import com.project.driverapp.presentation.register.RegisterViewModel
import com.project.driverapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val useCaseModule = module {
    single<AuthUseCase> { AuthInteractor(get()) }
    single<QuestionerUseCase> { QuestionerInteractor(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}

val networkModule = module {
    single {
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}

val repositoryModule = module {
    single { FirebaseAuth.getInstance() }
    single { RemoteDataSource(get()) }
    single<AppRepository> {
        AppRepositoryImpl(get())
    }
}