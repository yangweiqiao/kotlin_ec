package com.niu1078.usercenter.service

import rx.Observable

/**
 * Created by Administrator on 2017/12/12.
 */
interface UserService {

    fun register(mobile: String, password: String, verfcode: String): Observable<Boolean>

}