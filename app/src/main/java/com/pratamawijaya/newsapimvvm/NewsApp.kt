package com.pratamawijaya.newsapimvvm

import android.app.Application
import com.pratamawijaya.newsapimvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)
            modules(appModule)
        }

        Timber.plant(Timber.DebugTree())
    }
}