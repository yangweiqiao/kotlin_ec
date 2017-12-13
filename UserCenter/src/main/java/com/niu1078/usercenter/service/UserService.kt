package com.niu1078.usercenter.service

import rx.Observable


/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :Service
 * action:
 *
 */
interface UserService {

    fun register(mobile: String, password: String, verfcode: String): Observable<Boolean>

}