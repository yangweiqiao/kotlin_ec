package com.niu1078.usercenter.data.repository

import com.kotlin.user.data.protocol.ForgetPwdReq
import com.kotlin.user.data.protocol.ResetPwdReq
import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.api.UserApi
import com.niu1078.usercenter.data.protocol.LoginReq
import com.niu1078.usercenter.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :真正的去访问数据网络的
 * action:真正的去访问数据网络的
 *
 */

class UserRepository @Inject constructor() {
    //注册 方法和参数和userService一样
    fun register(mobile: String, password: String, code: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .register(RegisterReq(mobile, password, code)) //请求的实体
    }

    fun login(mobile: String, password: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .login(LoginReq(mobile, password, pushId)) //请求的实体
    }

    fun forgetPwd(mobile: String, code: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile, code)) //请求的实体
    }
    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile, pwd)) //请求的实体
    }
}