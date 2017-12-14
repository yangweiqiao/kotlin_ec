package com.niu1078.base.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.niu1078.base.R
import com.niu1078.base.ext.onClick
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * author :ywq .
 * time: 2017/12/14:13:55.
 * desc :
 * action:
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var isShowback = true
    private var titleText: String? = null
    private var rightText: String? = null

    //初始化
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowback = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)
        initView()
        typedArray.recycle()

    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)
        mLeftIv.visibility = if (isShowback) View.VISIBLE else View.INVISIBLE
        titleText?.let {
            mTitleTv.text = it
        }
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

        mLeftIv.onClick {

            if (context is Activity)
                (context as Activity).finish()
        }

    }


    fun getRightView(): TextView {
        return mRightTv
    }

}