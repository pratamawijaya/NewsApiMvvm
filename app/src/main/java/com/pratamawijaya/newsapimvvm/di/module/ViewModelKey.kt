package com.pratamawijaya.newsapimvvm.di.module

import android.arch.lifecycle.ViewModel
import com.pratamawijaya.newsapimvvm.ui.topheadline.TopHeadlineViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlineViewModel::class)
    abstract fun bindTopHeadlineViewModel(viewModel: TopHeadlineViewModel): ViewModel
}