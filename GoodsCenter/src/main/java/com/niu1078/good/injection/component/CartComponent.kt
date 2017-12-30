package com.niu1078.good.injection.component

import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.PerComponentScope
import com.niu1078.good.injection.module.CartModule
import com.niu1078.good.ui.fragment.CartFragment
import dagger.Component

/**
 * author :ywq .
 * time: 2017/12/25:20:02.
 * desc :购物车Component
 * action:
 */
/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)


}
