package com.niu1078.base.rx

import com.niu1078.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * author :ywq .
 * time: 2017/12/14:12:13.
 * desc :
 * action:
 */
class BaseFunBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        return if (t.status == 0) {
            Observable.just(true)
        } else {
            Observable.error(BaseException(t.status, t.message))
        }
    }
}