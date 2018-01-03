package com.niu1078.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.niu1078.base.common.AppManager
import com.niu1078.base.ext.enable
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.base.utils.encode
import com.niu1078.usercenter.R
import com.niu1078.usercenter.injection.component.DaggerUserComponent
import com.niu1078.usercenter.injection.module.USerModule
import com.niu1078.usercenter.presenter.p.RegisterPresenter
import com.niu1078.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
//扩展方法测试
        //1.基本
//        act_reg_tv_reg.setOnClickListener {
        //     mPresenter.register(act_home_et_phone.text.toString(), act_reg_et_pwd.text.toString(), act_home_et_sms_code.text.toString())

//        }
//扩展一
//        act_reg_tv_reg.onClick(View.OnClickListener {
//            mPresenter.register(act_home_et_phone.text.toString(), act_reg_et_pwd.text.toString(), act_home_et_sms_code.text.toString())
//        })
//扩展二
//        act_reg_tv_reg.onClick {
//            mPresenter.register(act_home_et_phone.text.toString(), act_reg_et_pwd.text.toString(), act_home_et_sms_code.text.toString())
//        }
//倒計時獲取驗證碼
//        frag_register_tv_getcode.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
//            override fun onClick() {
//                toast("获取验证码")
//            }
//        })
//        frag_register_tv_getcode.onClick(View.OnClickListener {
//            frag_register_tv_getcode.requestSendVerifyNumber()
//        })
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mRegister.enable(mUserPhone, { isbuttonEnable() })
        mRegister.enable(mUserPassword, { isbuttonEnable() })
        mRegister.enable(mUserPassword2, { isbuttonEnable() })
        mRegister.enable(mUserCode, { isbuttonEnable() })

        registerCode.onClick(this)
        mRegister.onClick(this)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).uSerModule(USerModule()).build().inject(this)
        mPresenter.mView = this
    }

    /**
     * 点击事件
     */
    override fun onClick(v: View) {

        when (v.id) {
            R.id.mRegister -> {
                mPresenter.register(mUserPhone.text.toString(), encode(mUserPassword.text.toString()) , mUserCode.text.toString())
            }
            R.id.registerCode -> {
                toast("发送验证码")
            }
        }

    }

    /**
     * 注册结果回调
     */
    override fun onRegisterResult(result: String) {

        toast(result)
    }

    private fun isbuttonEnable(): Boolean {

        return !mUserPhone.text.isNullOrEmpty() &&
                !mUserCode.text.isNullOrEmpty() &&
                !mUserPassword.text.isNullOrEmpty() &&
                !mUserPassword2.text.isNullOrEmpty()
    }


    private var time: Long = 0

    override fun onBackPressed() {

        val time2 = System.currentTimeMillis()
        if (time2 - time > 2000) {

            toast("再按一次退出... ")
            time = time2
        } else {
            AppManager.instance.exitApp(this)
        }


    }
}
