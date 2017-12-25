//package com.niu1078.base.injection.Component
//
//import android.content.Context
//import android.support.v4.app.Fragment
//import com.niu1078.base.injection.ActivityScope
//import com.niu1078.base.injection.module.FragmentModule
//import com.niu1078.base.injection.FragmentScope
//import com.niu1078.base.injection.module.LifecycleProviderModule
//import com.trello.rxlifecycle.LifecycleProvider
//import dagger.Component
//
///**
// * author :ywq .
// * time: 2017/12/25:16:36.
// * desc :
// * action:
// */
//
//@ActivityScope
//@Component(modules = arrayOf(FragmentModule::class, LifecycleProviderModule::class),dependencies = arrayOf(AppComponent::class))
//interface FragmentComponent {
//    fun fragment(): Fragment
//    fun lifecycleProvider(): LifecycleProvider<*>
//    fun context(): Context
//}