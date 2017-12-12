package com.niu1078.usercenter.data.protocol

import com.niu1078.base.data.protocol.BaseReq

/**
 * Created by Administrator on 2017/12/12.
 * 数据类
 * 这里面的决定的是参数的名字
 * 我们想传递一些公共参数 创建一个基类 在里面实现公共参数的传值
 * 请求的实体
 */
/**
 * 请求的实体
 */
data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String) : BaseReq()