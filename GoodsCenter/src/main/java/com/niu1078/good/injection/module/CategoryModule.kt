package com.niu1078.good.injection.module

import com.niu1078.good.service.CategoryService
import com.niu1078.good.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/25:16:28.
 * desc :分类 module
 * action:
 */
@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service: CategoryServiceImpl):CategoryService{

        return service
    }

}