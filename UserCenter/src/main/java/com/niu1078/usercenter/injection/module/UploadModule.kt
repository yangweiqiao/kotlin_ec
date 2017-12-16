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
 * desc :
 * action:
 */
@Module
class UploadModule {
    @Provides
    fun providesUploadService(service: UploadServiceImpl): UploadService {

        return service
    }

}