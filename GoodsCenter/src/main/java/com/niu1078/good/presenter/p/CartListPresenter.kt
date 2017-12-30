package com.niu1078.good.presenter.p

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.presenter.view.CartListView
import com.niu1078.good.service.CartService
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/30:12:17.
 * desc :购物车 Presenter
 * action:
 */
class CartListPresenter @Inject constructor():BasePresenter<CartListView>() {
    @Inject
    lateinit var cartService: CartService

    /*
        获取购物车列表
     */
    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.getCartList().excute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
            override fun onNext(t: MutableList<CartGoods>?) {
                mView.onGetCartListResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(list).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        提交购物车商品
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.submitCart(list,totalPrice).excute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        }, lifecycleProvider)

    }


}