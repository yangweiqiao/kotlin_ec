package com.niu1078.order.presenter.view

import com.niu1078.base.presenter.view.BaseView
import com.niu1078.order.data.protocol.Order

/**
 * author :ywq .
 * time: 2018/1/3:18:03.
 * desc :
 * action:
 */
interface OrderView  : BaseView{
    fun onGetOrderByIdResult(t: Order)


}