package com.niu1078.usercenter.ui.activity

import android.os.Bundle
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.utils.UserPrefsUtils
import com.niu1078.base.ext.enable
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.usercenter.R
import com.niu1078.usercenter.injection.component.DaggerUserComponent
import com.niu1078.usercenter.injection.module.USerModule
import com.niu1078.usercenter.presenter.p.LoginPresenter
import com.niu1078.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * author :ywq .
 * time: 2017/12/14:18:42.
 * desc :
 * action:
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {
    override fun onLoginResult(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
//        startActivity<UserInfoActivity>()
        finish()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).uSerModule(USerModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {

        mLogin.enable(username, { isbuttonEnable() })
        mLogin.enable(password, { isbuttonEnable() })

        mLogin.onClick {
            mPresenter.login(username.text.toString(), password.text.toString(), "pushid")
        }
        headerBar.getRightView().onClick {
            toast("注册界面 ")
            startActivity<RegisterActivity>()
        }

        forgotPassword.onClick {
            startActivity<ForgetPwdActivity>()
        }

    }

    private fun isbuttonEnable(): Boolean {

        return !username.text.isNullOrEmpty() &&
                !password.text.isNullOrEmpty()

    }

}