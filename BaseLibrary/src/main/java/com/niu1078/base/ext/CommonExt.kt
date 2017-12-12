package com.niu1078.base.ext

import com.niu1078.base.rx.BaseSubscriber
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Administrator on 2017/12/12.
 */
fun <T> Observable<T>.excute(subscriber:BaseSubscriber<T>)  {


     this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber )
}