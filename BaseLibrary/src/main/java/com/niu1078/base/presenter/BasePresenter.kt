package com.niu1078.base.presenter

import com.niu1078.base.presenter.view.BaseView

/**
 * Created by Administrator on 2017/12/12.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView :T

}