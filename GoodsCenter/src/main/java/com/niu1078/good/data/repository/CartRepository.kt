package com.niu1078.good.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.api.CartApi
import com.niu1078.good.data.protocol.AddCartReq
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.data.protocol.DeleteCartReq
import com.niu1078.good.data.protocol.SubmitCartReq
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/30:10:21.
 * desc :
 * action:
 */
class CartRepository @Inject constructor() {
    /*
         获取购物车列表
      */
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>> {
        return RetrofitFactory.instance.creat(CartApi::class.java).getCartList()
    }

    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResp<Int>> {
        return RetrofitFactory.instance.creat(CartApi::class.java)
                .addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(CartApi::class.java).deleteCartList(DeleteCartReq(list))
    }

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<BaseResp<Int>> {
        return RetrofitFactory.instance.creat(CartApi::class.java).submitCart(SubmitCartReq(list, totalPrice))
    }


}