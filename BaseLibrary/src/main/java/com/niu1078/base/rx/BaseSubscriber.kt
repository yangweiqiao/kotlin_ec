package com.niu1078.base.rx

import com.niu1078.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by Administrator on 2017/12/12.
 */
open class BaseSubscriber<T>(val baseView: BaseView) : Subscriber<T>() {
    override fun onNext(t: T) {

    }

    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        }
    }

}