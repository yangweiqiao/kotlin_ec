package com.niu1078.base.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.niu1078.base.R
import org.jetbrains.anko.find


/**
 * author :ywq .
 * time: 2017/12/14:14:22.
 * desc :ProgressLoading
 * action:   自定义加载对话框  继承系统的对话框
 */
class ProgressLoading private constructor(context: Context, themeResId: Int) : Dialog(context, themeResId) {
    //使用2个参数的构造方法
    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animDrawable: AnimationDrawable? = null
        //相当是java里面的静态方法
        fun creat(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER
            val lp = mDialog.window.attributes
            //设置灰暗程度
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp
            //播放进度动画
            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }
}