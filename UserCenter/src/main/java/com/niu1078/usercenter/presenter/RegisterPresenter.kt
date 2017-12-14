package com.niu1078.usercenter.presenter

import com.kotlin.base.utils.NetWorkUtils
import com.niu1078.base.common.AppManager
import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseException
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.usercenter.presenter.view.RegisterView
import com.niu1078.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/12.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun register(username: String, password: String, code: String) {
        /**
         * 业务逻辑
         */
        // val userService = UserServiceImpl()
        //判断网络是否可用
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.register(username, password, code)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onRegisterResult("注册成功")
                        }
                    }
                }, lifecycleProvider)


    }
}