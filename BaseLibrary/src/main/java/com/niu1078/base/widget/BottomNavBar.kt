package com.niu1078.base.widget

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.niu1078.base.R

/**
 * author :ywq .
 * time: 2017/12/16:14:49.
 * desc :
 * action:
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {
    private val mCartBadge: TextBadgeItem
    private val mMsgBadge: ShapeBadgeItem

    init {
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
                .setActiveColorResource(R.color.common_blue) //选择的字体颜色
                .setInActiveColorResource(R.color.text_normal) //未选择的字体颜色
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
                .setActiveColorResource(R.color.common_blue) //选择的字体颜色
                .setInActiveColorResource(R.color.text_normal) //未选择的字体颜色
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)

        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
                .setActiveColorResource(R.color.common_blue) //选择的字体颜色
                .setInActiveColorResource(R.color.text_normal) //未选择的字体颜色
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
        mCartBadge = TextBadgeItem()
        mCartBadge.setText("5")
        cartItem.setBadgeItem(mCartBadge)
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
                .setActiveColorResource(R.color.common_blue) //选择的字体颜色
                .setInActiveColorResource(R.color.text_normal) //未选择的字体颜色
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(0)
        msgItem.setBadgeItem(mMsgBadge)
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
                .setActiveColorResource(R.color.common_blue) //选择的字体颜色
                .setInActiveColorResource(R.color.text_normal) //未选择的字体颜色
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
        setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor(R.color.common_white)
                .addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText(count.toString())
        }


    }

    fun checkMsgBadge(count: Int) {
        if (count == 0) {
            mMsgBadge.hide()
        } else {
            mMsgBadge.show()
        }


    }
}