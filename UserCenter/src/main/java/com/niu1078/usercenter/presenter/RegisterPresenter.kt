package com.niu1078.usercenter.presenter

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseException
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.usercenter.presenter.view.RegisterView
import com.niu1078.usercenter.service.impl.UserSeviceImpl

/**
 * Created by Administrator on 2017/12/12.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(username: String, password: String, code: String) {
        /**
         * 业务逻辑
         */
        val userService = UserSeviceImpl()

        userService.register(username, password, code)
                .excute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                    override fun onError(e: Throwable?) {
                        e as BaseException

                        println("测试数据${e.msg}${e.status} " )
                    }
                })


    }
}