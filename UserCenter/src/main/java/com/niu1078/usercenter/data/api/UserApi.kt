package com.niu1078.usercenter.data.api

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/**
 * Created by Administrator on 2017/12/12.
 * 用户api
 */
interface UserApi {
    /**
     * 注册接口
     * 参数是请求的实体
     */
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>
}