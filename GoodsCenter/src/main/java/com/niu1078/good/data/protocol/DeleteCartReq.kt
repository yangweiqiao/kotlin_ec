package com.niu1078.good.data.protocol

/**
 * author :ywq .
 * time: 2017/12/30:10:24.
 * desc :
 * action:
 */
/*
    删除购物车商品请求
 */
data class DeleteCartReq(val cartIdList: List<Int> = arrayListOf())
