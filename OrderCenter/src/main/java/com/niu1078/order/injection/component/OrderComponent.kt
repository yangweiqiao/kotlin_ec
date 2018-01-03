package com.niu1078.order.injection.component

import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.PerComponentScope
import com.niu1078.order.injection.module.OrderModule
import com.niu1078.order.ui.activity.OrderConfirmActivity
import dagger.Component

/**
 * author :ywq .
 * time: 2018/1/3:17:30.
 * desc :
 * action:
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(orderConfirmActivity: OrderConfirmActivity)


    // fun inject (activity : )

}
