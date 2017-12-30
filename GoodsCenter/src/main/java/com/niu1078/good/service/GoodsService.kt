package com.niu1078.good.service

import com.niu1078.good.data.protocol.Goods
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/25:20:02.
 * desc :商品 业务层 接口
 * action:
 */

interface GoodsService {

    /*
        获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /*
        根据关键字查询商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /*
        获取商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}
