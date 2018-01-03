package com.niu1078.order.data.protocol

/**
 * author :ywq .
 * time: 2018/1/3:15:24.
 * desc :订单数据类
 * action:
 */
data class Order(val id :Int,
                 val payType: Int,
                 var shipAddress: ShipAddress?,
                 val totalPrice: Long,
                 var orderStatus: Int,
                 val orderGoodsList: MutableList<OrderGoods>

                 )