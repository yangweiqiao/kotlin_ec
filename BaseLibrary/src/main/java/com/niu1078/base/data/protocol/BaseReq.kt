package com.niu1078.base.data.protocol

/**
 * Created by Administrator on 2017/12/12.
 */
open class BaseReq(val appType: String = "1", val device: String = "1") {
    val token: String = appType
    val time: String = System.currentTimeMillis().toString()
}