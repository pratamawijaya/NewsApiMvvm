package com.pratamawijaya.newsapimvvm.di.components

import android.app.Application
import com.pratamawijaya.newsapimvvm.NewsApp
import com.pratamawijaya.newsapimvvm.di.module.*
import com.pratamawijaya.newsapimvvm.ui.main.MainActivityModule
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
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        MainActivityModule::class
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