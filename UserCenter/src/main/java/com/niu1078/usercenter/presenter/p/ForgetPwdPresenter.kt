package com.niu1078.usercenter.presenter.p

import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.usercenter.presenter.view.ForgetPwdView
import com.niu1078.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/12.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String,  verifyCode: String) {
        /**
         * 业务逻辑
         */
        // val userService = UserServiceImpl()
        //判断网络是否可用
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
if (t)
                            mView.onforgetPwdResult(t)

                    }
                }, lifecycleProvider)


    }





    fun resetPwd(mobile: String,  pwd: String) {
        /**
         * 业务逻辑
         */
        // val userService = UserServiceImpl()
        //判断网络是否可用
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onResetPwdResult(t)

                    }
                }, lifecycleProvider)


    }

}