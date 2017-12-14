package com.niu1078.base.injection.Component

import android.content.Context
import com.niu1078.base.injection.module.AppModule
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

/**
 * author :ywq .
 * time: 2017/12/14:10:28.
 * desc :
 * action:
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}