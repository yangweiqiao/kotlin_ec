package com.niu1078.usercenter.data.protocol

import com.niu1078.base.data.protocol.BaseReq

/**
 * author :ywq .
 * time: 2017/12/14:19:08.
 * desc :
 * action:
 */
data class LoginReq(val mobile:String, val pwd:String, val pushId:String ) :BaseReq()