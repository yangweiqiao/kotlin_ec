package com.niu1078.usercenter.ui.activity

import android.os.Bundle
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.usercenter.R
import com.niu1078.usercenter.presenter.RegisterPresenter
import com.niu1078.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.register_content.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {
    override fun onRegisterResult(result: Boolean) {

        toast("注册结果是$result")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter=RegisterPresenter()
        mPresenter.mView=this


        act_reg_tv_reg.setOnClickListener {
            mPresenter.register(act_home_et_phone.text.toString(),act_reg_et_pwd.text.toString(),act_home_et_sms_code.text.toString())
        }
    }
}
