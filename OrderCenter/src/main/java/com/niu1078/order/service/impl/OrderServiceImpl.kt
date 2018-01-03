package com.niu1078.order.service.impl

/**
 * author :ywq .
 * time: 2018/1/3:15:37.
 * desc :
 * action:
 */
import com.niu1078.base.ext.convert
import com.niu1078.base.ext.convertBoolean
import com.niu1078.order.data.protocol.Order
import com.niu1078.order.data.repository.OrderRepository
import com.niu1078.order.service.OrderService
import rx.Observable
import javax.inject.Inject

/*
    订单业务实现类
 */
class OrderServiceImpl @Inject constructor() : OrderService {

    @Inject
    lateinit var repository: OrderRepository

    /*
        根据ID查询订单
     */
    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }

    /*
        订单确认，提交订单
     */
    override fun submitOrder(order: Order): Observable<Boolean> {
        return repository.submitOrder(order).convertBoolean()
    }

    /*
        根据订单状态获取订单列表
     */
    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()

    }

    /*
        取消订单
     */
    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return repository.cancelOrder(orderId).convertBoolean()
    }

    /*
        订单确认收货
     */
    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }
}
