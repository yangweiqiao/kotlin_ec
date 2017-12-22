package com.niu1078.usercenter.injection.module

import com.niu1078.usercenter.service.UploadService
import com.niu1078.usercenter.service.UserService
import com.niu1078.usercenter.service.impl.UploadServiceImpl
import com.niu1078.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/16:13:44.
 * desc :接口不能实例化 只能通过实现类实例化 这里就要使用@module 在接口实现类的构造方法中 也要使用@inject 构造
 * action:
 */
@Module
class UploadModule {
    @Provides
    fun providesUploadService(service: UploadServiceImpl): UploadService {
        return service
    }

}