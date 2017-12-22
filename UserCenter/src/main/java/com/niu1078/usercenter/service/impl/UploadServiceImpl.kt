package com.niu1078.usercenter.service.impl

import com.niu1078.base.ext.convert
import com.niu1078.usercenter.data.repository.UploadRepository
import com.niu1078.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

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