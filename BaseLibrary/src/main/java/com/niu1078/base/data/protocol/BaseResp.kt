package com.niu1078.base.data.protocol

/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :响应基类
 * action:做网络访问的时候,响应数据里面包含的一些公共的参数 固定的格式
 */
class BaseResp<out T>(val status: Int, val data: T, val ok: Boolean, val message: String, val code: Int, val result: T)