package com.niu1078.good.data.api

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/21:15:05.
 * desc :
 * action:
 */
/*
    商品分类接口
 */
interface CategoryApi {
    /*
        获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}
