package com.niu1078.good.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.api.GoodsApi
import com.niu1078.good.data.protocol.GetGoodsDetailReq
import com.niu1078.good.data.protocol.GetGoodsListByKeywordReq
import com.niu1078.good.data.protocol.GetGoodsListReq
import com.niu1078.good.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/25:19:59.
 * desc :
 * action:
 */
/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.creat(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    /*
        根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.creat(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /*
        商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.instance.creat(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}
