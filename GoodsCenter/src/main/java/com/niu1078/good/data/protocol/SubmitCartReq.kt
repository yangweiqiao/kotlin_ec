package com.niu1078.good.data.protocol

import com.niu1078.base.data.protocol.BaseReq

/**
 * author :ywq .
 * time: 2017/12/30:10:24.
 * desc :
 * action:
 */
/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>,val totalPrice: Long) :BaseReq()
