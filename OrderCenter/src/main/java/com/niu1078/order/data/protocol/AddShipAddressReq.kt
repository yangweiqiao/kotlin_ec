package com.niu1078.order.data.protocol

/**
 * author :ywq .
 * time: 2018/1/3:15:29.
 * desc :
 * action:
 */
/*
    添加收货地址
 */
data class AddShipAddressReq(val shipUserName: String, val shipUserMobile: String, val shipAddress: String)
