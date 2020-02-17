package com.pratamawijaya.newsapimvvm

import android.app.Application
import com.pratamawijaya.newsapimvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        koinApplication {
            androidContext(this@NewsApp)
            modules(appModule)
        }

        Timber.plant(Timber.DebugTree())
    }
}