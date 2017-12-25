package com.niu1078.base.ui.activity

import android.os.Bundle
import com.niu1078.base.common.BaseApplication
import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.Component.DaggerActivityComponent

import com.niu1078.base.injection.module.ActivityModule
import com.niu1078.base.injection.module.LifecycleProviderModule
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.presenter.view.BaseView
import com.niu1078.base.widget.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/12.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    lateinit var activityComponent: ActivityComponent
    private lateinit var mLoadingProgress: ProgressLoading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
        injectComponent()
        mLoadingProgress = ProgressLoading.creat(this)

    }

    abstract fun injectComponent()

    private fun initActivityInject() {


        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication)
                .APPComponent).lifecycleProviderModule(LifecycleProviderModule(this)).activityModule(ActivityModule(this)).build()
    }

    override fun showLoading() {
        mLoadingProgress.showLoading()
    }

    override fun hideLoading() {
        mLoadingProgress.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T
}