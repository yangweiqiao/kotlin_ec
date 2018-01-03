package com.niu1078.order.data.protocol

/**
 * author :ywq .
 * time: 2018/1/3:15:29.
 * desc :
 * action:
 */
/*
    修改收货地址
 */
data class EditShipAddressReq(val id:Int,val shipUserName:String,val shipUserMobile:String,val shipAddress:String,val shipIsDefault:Int)
