package com.niu1078.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.niu1078.base.R
import com.niu1078.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

/**
 * Created by Administrator on 2017/12/12.
 *
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //入栈
        AppManager.instance.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        //出栈
        AppManager.instance.finishActivity(this)

    }
val contentView :View
  get() {
      val content = find<FrameLayout>(android.R.id.content)
      return  content.getChildAt(0)
  }
}