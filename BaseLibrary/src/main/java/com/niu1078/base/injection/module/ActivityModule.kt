package com.niu1078.base.injection.module

import android.app.Activity
import android.content.Context
import com.niu1078.base.common.BaseApplication
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
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {

        return activity
    }
}