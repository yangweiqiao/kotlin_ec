package com.niu1078.base.injection.Component

import android.app.Activity
import android.content.Context
import com.niu1078.base.injection.ActivityScope
import com.niu1078.base.injection.module.ActivityModule
import com.niu1078.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * author :ywq .
 * time: 2017/12/14:10:28.
 * desc :
 * action:
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class),dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun activity(): Activity
    fun lifecycleProvider(): LifecycleProvider<*>
    fun context(): Context
}