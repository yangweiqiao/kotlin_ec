package com.niu1078.usercenter.presenter.view

import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.presenter.view.BaseView

/**
 * Created by Administrator on 2017/12/12.
 */
   interface LoginView :BaseView{

    fun onLoginResult(result  : UserInfo)

}