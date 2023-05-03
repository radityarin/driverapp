package com.project.driverapp

import android.app.Application
import androidx.multidex.BuildConfig
import com.chibatching.kotpref.Kotpref
import com.project.driverapp.di.networkModule
import com.project.driverapp.di.repositoryModule
import com.project.driverapp.di.useCaseModule
import com.project.driverapp.di.viewModelModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    networkModule,
                    viewModelModule
                )
            )
        }
        FirebaseApp.initializeApp(this);
        Kotpref.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}