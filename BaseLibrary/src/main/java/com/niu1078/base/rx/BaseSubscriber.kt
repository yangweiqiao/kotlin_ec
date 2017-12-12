package com.niu1078.base.rx

import rx.Subscriber

/**
 * Created by Administrator on 2017/12/12.
 */
open class BaseSubscriber<T> :Subscriber<T>(){
    override fun onNext(t: T) {

    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }

}