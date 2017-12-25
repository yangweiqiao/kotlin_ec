package com.niu1078.good.data.repository

import com.niu1078.base.data.net.RetrofitFactory
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.good.data.api.CategoryApi
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/13:18:14.
 * desc :真正的去访问数据网络的
 * action:真正的去访问数据网络的
 *
 */

class CategoryRepository @Inject constructor() {
    //注册 方法和参数和userService一样
    fun category(id:Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.creat(CategoryApi::class.java)
                .getCategory(GetCategoryReq(id )) //请求的实体
    }



}