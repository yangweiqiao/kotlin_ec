package com.niu1078.base.injection.module

import android.content.Context
import com.niu1078.base.common.BaseApplication
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * author :ywq .
 * time: 2017/12/14:10:29.
 * desc :
 * action:
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider:  LifecycleProvider<*>) {

    @Provides
    fun providelifecycleProvider(): LifecycleProvider<*> {

        return lifecycleProvider
    }
}