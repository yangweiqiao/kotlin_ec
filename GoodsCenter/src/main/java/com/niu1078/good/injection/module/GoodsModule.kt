package com.niu1078.good.injection.module

import com.niu1078.good.service.GoodsService
import com.niu1078.good.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/25:20:01.
 * desc :
 * action:
 */
/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}
