package com.niu1078.base.ui.activity

import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.presenter.view.BaseView

/**
 * Created by Administrator on 2017/12/12.
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
lateinit var mPresenter : T
}