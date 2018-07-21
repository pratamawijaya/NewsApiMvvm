package com.pratamawijaya.newsapimvvm.di.module

import com.pratamawijaya.newsapimvvm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by @ilhamsuaib on 7/21/18.
 * github.com/ilhamsuaib
 */

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun mainActivity(): MainActivity
}