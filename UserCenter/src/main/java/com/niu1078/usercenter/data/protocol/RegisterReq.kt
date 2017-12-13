package com.niu1078.usercenter.data.protocol

import com.niu1078.base.data.protocol.BaseReq


/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :请求的实体
 * action:做网络访问的时候,请求体,这里面的决定的是参数的名字
 * 这个包里面放的是请求体
 */

data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String) : BaseReq()