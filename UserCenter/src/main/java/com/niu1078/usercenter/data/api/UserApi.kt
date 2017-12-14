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
interface UserApi {
    /**
     * 注册接口
     * 参数是请求的实体
     * 返回值本来是BaseResp 和Rx相关的 用Observable来接受  BaseResp<String> String就是传入的泛型
     */
    @POST("userCenter/register")  //标注请求的方法和地址
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")  //标注请求的方法和地址
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>



    /*
        忘记密码
     */
    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq):Observable<BaseResp<String>>

    /*
        重置密码
     */
    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq):Observable<BaseResp<String>>

    /*
        编辑用户资料
     */
    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq):Observable<BaseResp<UserInfo>>

}