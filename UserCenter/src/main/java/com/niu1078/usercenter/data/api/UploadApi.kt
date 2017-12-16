package com.niu1078.usercenter.data.api

import com.kotlin.user.data.protocol.EditUserReq
import com.kotlin.user.data.protocol.ForgetPwdReq
import com.kotlin.user.data.protocol.ResetPwdReq
import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.usercenter.data.protocol.LoginReq
import com.niu1078.usercenter.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :接口
 * action:提供注册功能的接口
 */
interface UploadApi {

    /*
        编辑用户资料
     */
    @POST("common/getUploadToken")
    fun getUploadToken( ):Observable<BaseResp<String>>

}