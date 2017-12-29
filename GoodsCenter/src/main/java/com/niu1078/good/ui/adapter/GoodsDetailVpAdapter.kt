package com.niu1078.good.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.niu1078.good.ui.fragment.GoodsDetailTabOneFragment
import com.niu1078.good.ui.fragment.GoodsDetailTabTwoFragment

/**
 * author :ywq .
 * time: 2017/12/29:17:32.
 * desc :
 * action:
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return GoodsDetailTabOneFragment()
        } else {
            return GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    private val titles = arrayOf("商品", "详情")
    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}