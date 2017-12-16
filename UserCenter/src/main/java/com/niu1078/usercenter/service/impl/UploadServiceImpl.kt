package com.niu1078.usercenter.service.impl

import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.ext.convert
import com.niu1078.usercenter.data.repository.UserRepository
import com.niu1078.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject
import com.niu1078.base.ext.convertBoolean
import com.niu1078.usercenter.data.repository.UploadRepository
import com.niu1078.usercenter.service.UploadService

/**
 * Created by Administrator on 2017/12/12.
 */
class UploadServiceImpl @Inject constructor() : UploadService {
    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken( )
                .convert()
    }

    @Inject
    lateinit var repository: UploadRepository




}