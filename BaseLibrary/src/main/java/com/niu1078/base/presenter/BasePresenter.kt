package com.niu1078.base.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.niu1078.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * P层 基类
 * Created by Administrator on 2017/12/12.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T


    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork(): Boolean {
        val netWorkAvailable = NetWorkUtils.isNetWorkAvailable(context)
        return if (netWorkAvailable) {
            true
        } else {
            mView.onError("网络不可用")
            false
        }

    }
}