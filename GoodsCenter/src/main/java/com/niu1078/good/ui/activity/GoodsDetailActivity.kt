package com.niu1078.good.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.good.R
import com.niu1078.good.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * author :ywq .
 * time: 2017/12/29:16:28.
 * desc :商品详情界面
 * action:
 */
class GoodsDetailActivity : BaseActivity (){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode= TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter =GoodsDetailVpAdapter(supportFragmentManager , this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

    }


}