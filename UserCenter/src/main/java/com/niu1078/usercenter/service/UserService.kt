package com.niu1078.usercenter.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable


/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :Service
 * action:
 *
 */
interface UserService {

    fun register(mobile: String, password: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, password: String ,pushId:String): Observable<UserInfo>
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>

}