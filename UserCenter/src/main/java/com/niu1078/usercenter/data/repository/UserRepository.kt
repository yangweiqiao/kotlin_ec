package com.niu1078.usercenter.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.api.UserApi
import com.niu1078.usercenter.data.protocol.RegisterReq
import rx.Observable
/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :真正的去访问数据网络的
 * action:真正的去访问数据网络的
 *
 */

class UserRepository {
    //注册 方法和参数和userService一样
    fun register(mobile: String, password: String,code :String ): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .register(RegisterReq(mobile, password,code)) //请求的实体
    }
}