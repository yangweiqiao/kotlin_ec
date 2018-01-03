package com.niu1078.order.data.api

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.order.data.protocol.AddShipAddressReq
import com.niu1078.order.data.protocol.DeleteShipAddressReq
import com.niu1078.order.data.protocol.EditShipAddressReq
import com.niu1078.order.data.protocol.ShipAddress
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * author :ywq .
 * time: 2018/1/3:15:33.
 * desc :
 * action:
 */

/*
    地址管理 接口
 */
interface ShipAddressApi {

    /*
        添加收货地址
     */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResp<String>>

    /*
        删除收货地址
     */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResp<String>>

    /*
        修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressReq): Observable<BaseResp<String>>

    /*
        查询收货地址列表
     */
    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>>

}
