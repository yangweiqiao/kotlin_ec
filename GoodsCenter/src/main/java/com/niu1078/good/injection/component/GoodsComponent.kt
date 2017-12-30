package com.niu1078.good.injection.component

import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.PerComponentScope
import com.niu1078.good.injection.module.GoodsModule
import com.niu1078.good.ui.activity.GoodsDetailActivity
import com.niu1078.good.ui.activity.GoodsListActivity
import com.niu1078.good.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * author :ywq .
 * time: 2017/12/25:20:02.
 * desc :
 * action:
 */
/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class ))
interface GoodsComponent {
    fun inject(activity: GoodsListActivity)
    fun inject(activity: GoodsDetailActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)

}
