package com.niu1078.good.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.AppPrefsUtils
import com.niu1078.base.common.BaseConstant
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.good.R
import com.niu1078.good.event.AddCartEvent
import com.niu1078.good.ui.adapter.GoodsDetailVpAdapter
import com.niu1078.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * author :ywq .
 * time: 2017/12/29:16:28.
 * desc :商品详情界面
 * action:里面没处理什么 主要是viewpager绑定了2个Fragment
 */
class GoodsDetailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()

    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
        //加入购物车按钮
        mAddCartBtn.onClick {
            //需要判断用户是不是已经登录了
            if (AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN) == "") {
                //没有登录

                ARouter.getInstance().build(RouterPath.userCenter.path_login).navigation()
            } else {
//已经登录  发出一个事件  看谁接受
                Bus.send(AddCartEvent())
            }
        }
    }


}