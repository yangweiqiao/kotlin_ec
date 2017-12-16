package com.niu1078.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.niu1078.R
import com.niu1078.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNavBar.checkCartBadge(0)
        mBottomNavBar.checkMsgBadge(1)

        initView()
    }

    private fun initView() {
        val manger = supportFragmentManager.beginTransaction()
        manger.replace(R.id.mContaier, HomeFragment())
        manger.commit()
    }
}
