package com.niu1078.good.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.AppPrefsUtils
import com.niu1078.base.common.BaseConstant
import com.niu1078.base.ext.myToast
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.good.R
import com.niu1078.good.common.GoodsConstant
import com.niu1078.good.event.AddCartEvent
import com.niu1078.good.event.UpdateCartSizeEvent
import com.niu1078.good.ui.adapter.GoodsDetailVpAdapter
import com.niu1078.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*
import q.rorbin.badgeview.QBadgeView

/**
 * author :ywq .
 * time: 2017/12/29:16:28.
 * desc :商品详情界面
 * action:里面没处理什么 主要是viewpager绑定了2个Fragment
 */
class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBdage: QBadgeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
        initObserve()
        loadCartSize()
    }

    private fun loadCartSize() {
        setCartBdage()

    }

    private fun initObserve() {
        //购物车数量监听
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    myToast("购物车数量是 :${AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)}")
                    setCartBdage()

                }
                .registerInBus(this)

    }

    private fun setCartBdage() {
        //位置
       // mCartBdage.badgeGravity (Gravity.END  and  Gravity.TOP)
        mCartBdage.badgeGravity= Gravity.END or Gravity.TOP
        //偏移量
        mCartBdage.setGravityOffset(22f, -2f, true)

        mCartBdage.setBadgeTextSize(8f, true)

        mCartBdage.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
        //加入购物车按钮
        mAddCartBtn.onClick {
            //已经登录  发出一个事件  看谁接受
            Bus.send(AddCartEvent())

        }
        mCartBdage = QBadgeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        Bus.unregister(this)

    }
}