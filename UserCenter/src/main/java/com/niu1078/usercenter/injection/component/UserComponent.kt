package com.niu1078.usercenter.injection.component

import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.PerComponentScope
import com.niu1078.usercenter.injection.module.USerModule
import com.niu1078.usercenter.ui.activity.LoginActivity
import com.niu1078.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * author :ywq .
 * time: 2017/12/14:9:51.
 * desc :
 * action:
 */
@PerComponentScope
@Component(modules = arrayOf(USerModule::class) ,dependencies = arrayOf(ActivityComponent::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
}