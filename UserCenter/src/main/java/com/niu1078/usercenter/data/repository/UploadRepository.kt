package com.niu1078.usercenter.data.repository

import com.kotlin.user.data.protocol.ForgetPwdReq
import com.kotlin.user.data.protocol.ResetPwdReq
import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.api.UploadApi
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

class UploadRepository @Inject constructor() {

    fun getUploadToken( ): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UploadApi::class.java)
                .getUploadToken( ) //请求的实体
    }

}