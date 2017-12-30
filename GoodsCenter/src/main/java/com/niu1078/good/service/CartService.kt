package com.niu1078.good.service

import com.niu1078.good.data.protocol.CartGoods
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/30:10:18.
 * desc :购物车 业务层 接口
 * action:
 */
interface CartService {
    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /*
        购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}
