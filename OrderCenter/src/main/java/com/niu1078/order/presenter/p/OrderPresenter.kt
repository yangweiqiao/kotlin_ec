package com.niu1078.order.presenter.p

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.order.data.protocol.Order
import com.niu1078.order.presenter.view.OrderView
import com.niu1078.order.service.OrderService
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2018/1/3:18:02.
 * desc :
 * action:
 */
class OrderPresenter @Inject constructor(): BasePresenter<OrderView>() {

    @Inject
    lateinit var service: OrderService

    /*
        获取购物车列表
     */
    fun getOrderById(id:Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.getOrderById(id).excute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)

    }


}