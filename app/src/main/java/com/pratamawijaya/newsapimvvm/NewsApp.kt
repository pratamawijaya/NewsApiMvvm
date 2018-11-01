package com.pratamawijaya.newsapimvvm

import android.app.Application
import com.pratamawijaya.newsapimvvm.di.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))

        Timber.plant(Timber.DebugTree())
    }
}