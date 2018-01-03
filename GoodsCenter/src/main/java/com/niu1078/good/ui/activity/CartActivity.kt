package com.niu1078.good.ui.activity

import android.app.Fragment
import android.os.Bundle
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.good.R
import com.niu1078.good.ui.fragment.CartFragment

/**
 * author :ywq .
 * time: 2018/1/3:13:21.
 * desc :购物车界面 就是Fragment的一个外壳
 * action:
 */
class CartActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentCart)
        fragment as CartFragment
        fragment.setbackVisible(true)
    }

}