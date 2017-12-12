package com.niu1078.usercenter.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.api.UserApi
import com.niu1078.usercenter.data.protocol.RegisterReq
import rx.Observable

/**
 * 真正的去访问数据网络的
 * Created by Administrator on 2017/12/12.
 */
class UserRepository {
    fun register(mobile: String, password: String,code :String ): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .register(RegisterReq(mobile, password,code)) //请求的实体
    }
}