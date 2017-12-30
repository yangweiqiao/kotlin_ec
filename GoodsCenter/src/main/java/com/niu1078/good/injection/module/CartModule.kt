package com.niu1078.good.injection.module

import com.niu1078.good.service.CartService
import com.niu1078.good.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/30:10:29.
 * desc :购物车module
 * action:
 */
@Module
class CartModule {
    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {

        return service
    }

}