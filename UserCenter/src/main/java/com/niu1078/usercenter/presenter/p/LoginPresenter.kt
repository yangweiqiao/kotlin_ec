package com.niu1078.usercenter.presenter.p

import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.usercenter.presenter.view.LoginView
import com.niu1078.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/12.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, password: String, pushId: String) {
        /**
         * 业务逻辑
         */
        // val userService = UserServiceImpl()
        //判断网络是否可用
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.login(mobile, password ,pushId)
                .excute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {

                            mView.onLoginResult(t)

                    }
                }, lifecycleProvider)


    }
}