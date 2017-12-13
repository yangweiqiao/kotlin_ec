package com.niu1078.base.rx

/**
 * author :ywq .
 * time: 2017/12/13:18:35.
 * desc :异常处理
 * action:接受服务器返回的异常信息
 */
class BaseException(val status: Int, val msg: String) : Throwable() {

}
