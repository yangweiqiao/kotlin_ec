package com.niu1078.usercenter.ui.activity

import android.os.Bundle
import com.niu1078.base.ext.enable
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.usercenter.R
import com.niu1078.usercenter.injection.component.DaggerUserComponent
import com.niu1078.usercenter.injection.module.USerModule
import com.niu1078.usercenter.presenter.p.ForgetPwdPresenter
import com.niu1078.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.toast

/**
 * author :ywq .
 * time: 2017/12/14:18:42.
 * desc :
 * action:
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView {
    override fun onResetPwdResult(result: Boolean) {
         toast("密码修改成功")
    }

    override fun onforgetPwdResult(result: Boolean) {
        toast("${result}")
        if (result){
            mPresenter.resetPwd(mUserPhonef.text.toString(),  mUserPasswordf.text.toString())
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).uSerModule(USerModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    private fun initView() {

        mForgetPwd.enable(mUserPhonef, { isbuttonEnable() })
        mForgetPwd.enable(mUserCodef, { isbuttonEnable() })
        mForgetPwd.enable(mUserPasswordf, { isbuttonEnable() })
        mForgetPwd.enable(mUserPassword2f, { isbuttonEnable() })

        mForgetPwd.onClick {
            mPresenter.forgetPwd(mUserPhonef.text.toString(),  mUserCodef.text.toString())
        }

    }

    private fun isbuttonEnable(): Boolean {

        return !mUserPhonef.text.isNullOrEmpty() &&
                !mUserCodef.text.isNullOrEmpty() &&
                !mUserPasswordf.text.isNullOrEmpty() &&
                !mUserPassword2f.text.isNullOrEmpty()

    }

}