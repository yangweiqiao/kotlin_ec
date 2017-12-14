package com.niu1078.base.ui.activity

import android.os.Bundle
import com.niu1078.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Created by Administrator on 2017/12/12.
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)

    }

}