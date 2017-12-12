package com.niu1078.base.data.protocol

/**
 * Created by Administrator on 2017/12/12.
 */
class BaseResp<out T>(
        val status: Int,
        val message: String,
        val data: T
)