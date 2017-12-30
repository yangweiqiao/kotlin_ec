package com.niu1078.good.data.api

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.protocol.AddCartReq
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.data.protocol.DeleteCartReq
import com.niu1078.good.data.protocol.SubmitCartReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/30:10:23.
 * desc :
 * action:
 */
/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
