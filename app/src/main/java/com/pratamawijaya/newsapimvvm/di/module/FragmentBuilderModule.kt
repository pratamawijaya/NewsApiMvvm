package com.pratamawijaya.newsapimvvm.di.module

import com.pratamawijaya.newsapimvvm.ui.topheadline.TopHeadlineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by @ilhamsuaib on 7/21/18.
 * github.com/ilhamsuaib
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun topHeadlineFragment(): TopHeadlineFragment
}