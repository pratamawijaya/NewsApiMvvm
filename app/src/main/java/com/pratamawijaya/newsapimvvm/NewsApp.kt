package com.pratamawijaya.newsapimvvm

import android.app.Activity
import android.app.Application
import com.pratamawijaya.newsapimvvm.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class NewsApp : Application(), HasActivityInjector {


    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

        Timber.plant(Timber.DebugTree())
    }
}