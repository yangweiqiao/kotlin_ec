package com.niu1078.order.injection.module

import com.niu1078.order.service.OrderService
import com.niu1078.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2018/1/3:17:28.
 * desc :
 * action:
 */
@Module
class  OrderModule{
    @Provides
    fun  providesOrderService (service: OrderServiceImpl): OrderService{
        return  service
    }

}


