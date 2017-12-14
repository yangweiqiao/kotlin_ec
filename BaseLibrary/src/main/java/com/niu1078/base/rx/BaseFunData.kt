package com.niu1078.base.rx

import com.niu1078.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * author :ywq .
 * time: 2017/12/14:12:18.
 * desc :
 * action:
 */
class BaseFunData<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        return if (t.status == 0) {
            Observable.just(t.data)
        } else {
            Observable.error(BaseException(t.status, t.message))
        }
    }
}