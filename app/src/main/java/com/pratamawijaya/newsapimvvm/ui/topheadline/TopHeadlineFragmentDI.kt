package com.pratamawijaya.newsapimvvm.ui.topheadline

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Subcomponent
interface TopHeadlineFragmentSubcomponent : AndroidInjector<TopHeadlineFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TopHeadlineFragment>()
}

@Module(subcomponents = arrayOf(TopHeadlineFragmentSubcomponent::class))
abstract class TopHeadlineFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(TopHeadlineFragment::class)
    abstract fun bindToTopHeadlineFragmentInjectorFactory(builder: TopHeadlineFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}