package com.niu1078.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.niu1078.R
import com.niu1078.good.ui.fragment.CartFragment
import com.niu1078.good.ui.fragment.CategoryFragment
import com.niu1078.ui.fragment.HomeFragment
import com.niu1078.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMessageFragment by lazy { CartFragment() }
    private val mMeFragment by lazy { MeFragment() }

    private val mStack = Stack<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNavBar.checkCartBadge(0)
        mBottomNavBar.checkMsgBadge(1)

      //  initView()
        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    private fun initBottomNav() {

        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()

    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mHomeFragment)
        manager.add(R.id.mContaier, mCategoryFragment)
        manager.add(R.id.mContaier, mCartFragment)
        manager.add(R.id.mContaier, mMessageFragment)
        manager.add(R.id.mContaier, mMeFragment)
        manager.commit()
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMessageFragment)
        mStack.add(mMeFragment)

    }

    private fun initView() {
        val manger = supportFragmentManager.beginTransaction()
        manger.replace(R.id.mContaier, HomeFragment())
        manger.commit()
    }


}
