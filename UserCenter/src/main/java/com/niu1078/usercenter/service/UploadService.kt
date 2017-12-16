package com.niu1078.usercenter.service

import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/16:13:45.
 * desc :
 * action:
 */
interface UploadService {
    fun getUploadToken():Observable<String>

}