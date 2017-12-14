package com.niu1078.usercenter.injection.module

import com.niu1078.usercenter.service.UserService
import com.niu1078.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/14:9:44.
 * desc :用户Module
 * action:
 */
@Module
class USerModule {
     @Provides
    fun providesUserService(service: UserServiceImpl):UserService{

        return service
    }

}