package com.niu1078.good.data.api

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.protocol.GetGoodsDetailReq
import com.niu1078.good.data.protocol.GetGoodsListByKeywordReq
import com.niu1078.good.data.protocol.GetGoodsListReq
import com.niu1078.good.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/25:19:56.
 * desc :
 * action:
 */
/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
