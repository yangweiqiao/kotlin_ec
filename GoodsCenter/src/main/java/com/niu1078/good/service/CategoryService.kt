package com.niu1078.good.service

import com.niu1078.good.data.protocol.Category
import rx.Observable

/**
 * author :ywq .
 * time: 2017/12/25:16:27.
 * desc :
 * action:
 */
interface CategoryService {

    fun category(id:Int): Observable<MutableList<Category>?>

}