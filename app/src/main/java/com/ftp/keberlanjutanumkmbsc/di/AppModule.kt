package com.ftp.keberlanjutanumkmbsc.di

import com.ftp.keberlanjutanumkmbsc.data.source.AppRepositoryImpl
import com.ftp.keberlanjutanumkmbsc.data.source.remote.RemoteDataSource
import com.ftp.keberlanjutanumkmbsc.domain.repositories.AppRepository
import com.ftp.keberlanjutanumkmbsc.domain.usecases.auth.AuthInteractor
import com.ftp.keberlanjutanumkmbsc.domain.usecases.auth.AuthUseCase
import com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner.QuestionerInteractor
import com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner.QuestionerUseCase
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.account.AccountViewModel
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.history.HistoryViewModel
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.QuestionerViewModel
import com.ftp.keberlanjutanumkmbsc.presentation.login.LoginViewModel
import com.ftp.keberlanjutanumkmbsc.presentation.register.RegisterViewModel
import com.ftp.keberlanjutanumkmbsc.utils.Constants
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
    viewModel { QuestionerViewModel(get()) }
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