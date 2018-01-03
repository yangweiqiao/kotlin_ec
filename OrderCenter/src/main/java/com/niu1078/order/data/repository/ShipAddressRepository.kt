package com.niu1078.order.data.repository
import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.order.data.api.ShipAddressApi
import com.niu1078.order.data.protocol.AddShipAddressReq
import com.niu1078.order.data.protocol.DeleteShipAddressReq
import com.niu1078.order.data.protocol.EditShipAddressReq
import com.niu1078.order.data.protocol.ShipAddress
import javax.inject.Inject
import rx.Observable
/**
 * author :ywq .
 * time: 2018/1/3:15:35.
 * desc :
 * action:
 */
/*
   收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(ShipAddressApi::class.java).addShipAddress(AddShipAddressReq(shipUserName,shipUserMobile,shipAddress))
    }

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(ShipAddressApi::class.java).deleteShipAddress(DeleteShipAddressReq(id))
    }

    /*
        修改收货地址
     */
    fun editShipAddress(address: ShipAddress): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(ShipAddressApi::class.java).editShipAddress(EditShipAddressReq(address.id,address.shipUserName,address.shipUserMobile,address.shipAddress,address.shipIsDefault))
    }

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>> {
        return RetrofitFactory.instance.creat(ShipAddressApi::class.java).getShipAddressList()
    }

}