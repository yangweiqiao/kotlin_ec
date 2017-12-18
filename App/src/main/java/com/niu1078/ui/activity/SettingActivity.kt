package com.niu1078.ui.activity

import android.os.Bundle
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.user.utils.UserPrefsUtils
import com.niu1078.R
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * author :ywq .
 * time: 2017/12/18:16:05.
 * desc :
 * action:
 */
class SettingActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)

        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }

}