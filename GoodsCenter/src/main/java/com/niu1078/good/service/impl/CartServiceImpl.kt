package com.niu1078.good.service.impl

import com.niu1078.base.ext.convert
import com.niu1078.base.ext.convertBoolean
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.data.repository.CartRepository
import com.niu1078.good.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/30:10:19.
 * desc :  购物车 业务层 实现类
 * action:
 */
class CartServiceImpl @Inject constructor(): CartService{
    @Inject
    lateinit var repository: CartRepository

    /*
        加入购物车
     */
    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,
                goodsCount,goodsSku).convert()
    }

    /*
        获取购物车列表
     */
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    /*
        删除购物车商品
     */
    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(list).convertBoolean()
    }

    /*
        提交购物车商品
     */
    override fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(list,totalPrice).convert()
    }
}