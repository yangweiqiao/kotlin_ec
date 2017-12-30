package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.niu1078.base.ext.myToast
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.good.R
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.event.UpdateCartSizeEvent
import com.niu1078.good.injection.component.DaggerCartComponent
import com.niu1078.good.injection.module.CartModule
import com.niu1078.good.presenter.p.CartListPresenter
import com.niu1078.good.presenter.view.CartListView

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :购物车
 * action:
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {
    override fun onDeleteCartListResult(result: Boolean) {

    }

    override fun onSubmitCartListResult(result: Int) {

    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
//activity.myToast("购物车列表")

        println("购物车列表$result")
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(fragmentComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this


    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initobserve()
    }

    private fun initobserve() {
        //这个用着感觉比eventBus爽
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    /*
                       这有个问题  不知道为什么会接受2次事件
                     */
                    activity.myToast("接受事件,购物车里面应该有变化了哦 ")
                    mPresenter.getCartList()
                }
                .registerInBus(this)

    }

    //界面销毁的时候  注意的是bus事件的销毁
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}