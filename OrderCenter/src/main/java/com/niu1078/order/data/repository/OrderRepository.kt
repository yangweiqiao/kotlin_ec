package com.niu1078.order.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.order.data.api.OrderApi
import com.niu1078.order.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2018/1/3:15:34.
 * desc :
 * action:
 */
/*
   订单数据层
 */
class OrderRepository @Inject constructor() {

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(OrderApi::class.java).cancelOrder(CancelOrderReq(orderId))
    }

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(OrderApi::class.java).confirmOrder(ConfirmOrderReq(orderId))
    }

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>> {
        return RetrofitFactory.instance.creat(OrderApi::class.java).getOrderById(GetOrderByIdReq(orderId))
    }

    /*
        根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResp<MutableList<Order>?>> {
        return RetrofitFactory.instance.creat(OrderApi::class.java).getOrderList(GetOrderListReq(orderStatus))
    }

    /*
        提交订单
     */
    fun submitOrder(order: Order): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(OrderApi::class.java).submitOrder(SubmitOrderReq(order))
    }

}
