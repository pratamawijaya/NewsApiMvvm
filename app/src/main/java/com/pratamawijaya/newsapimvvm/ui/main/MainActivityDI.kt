package com.pratamawijaya.newsapimvvm.ui.main

import android.app.Activity
import com.pratamawijaya.newsapimvvm.ui.topheadline.TopHeadlineFragmentModule
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module(subcomponents = arrayOf(
        MainActivitySubComponent::class
))
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Subcomponent(modules = arrayOf(
        TopHeadlineFragmentModule::class
))
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}