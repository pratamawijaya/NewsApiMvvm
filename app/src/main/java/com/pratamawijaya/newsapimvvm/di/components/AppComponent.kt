package com.pratamawijaya.newsapimvvm.di.components

import android.app.Application
import com.pratamawijaya.newsapimvvm.NewsApp
import com.pratamawijaya.newsapimvvm.di.module.AppModule
import com.pratamawijaya.newsapimvvm.di.module.NetworkModule
import com.pratamawijaya.newsapimvvm.di.module.RepositoryModule
import com.pratamawijaya.newsapimvvm.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
        // etc ... activity module
))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: NewsApp)
}