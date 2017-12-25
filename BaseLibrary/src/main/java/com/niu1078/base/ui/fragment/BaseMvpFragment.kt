package com.niu1078.base.ui.fragment

import android.os.Bundle
import com.niu1078.base.common.BaseApplication
import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.Component.DaggerActivityComponent
import com.niu1078.base.injection.module.ActivityModule
import com.niu1078.base.injection.module.LifecycleProviderModule
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/12.
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    lateinit var fragmentComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInject()
        injectComponent()
    }

   abstract fun  injectComponent()

    private fun initActivityInject() {
        fragmentComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication)
                .APPComponent).lifecycleProviderModule(LifecycleProviderModule(this)).activityModule(ActivityModule(activity)) .build()


    }
    override fun showLoading() {

    }
    override fun hideLoading() {
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T
}