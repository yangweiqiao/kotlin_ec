package com.niu1078.order.data.protocol

/**
 * author :ywq .
 * time: 2018/1/3:15:27.
 * desc :
 * action:
 */
/*
   订单中的商品
 */
data class OrderGoods(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
)

