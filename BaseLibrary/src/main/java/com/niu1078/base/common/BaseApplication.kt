package com.niu1078.base.common

import android.app.Application
import android.content.Context
import com.niu1078.base.injection.Component.AppComponent
import com.niu1078.base.injection.Component.DaggerAppComponent
import com.niu1078.base.injection.module.AppModule

/**
 * author :ywq .
 * time: 2017/12/14:10:29.
 * desc :
 * action:
 */
class BaseApplication : Application() {
    lateinit var APPComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        context = this
        initAPPInject()

    }

    private fun initAPPInject() {

        APPComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    }

    companion object {
      lateinit var    context: Context
    }
}