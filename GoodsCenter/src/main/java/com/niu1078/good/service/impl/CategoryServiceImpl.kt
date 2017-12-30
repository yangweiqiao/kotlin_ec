package com.niu1078.good.service.impl

import com.niu1078.base.ext.convert
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.data.repository.CategoryRepository
import com.niu1078.good.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/25:16:28.
 * desc :  分类 业务层 实现类
 * action:
 */
class CategoryServiceImpl @Inject constructor() :CategoryService{

    @Inject
    lateinit var repository: CategoryRepository



    override fun category(id: Int): Observable<MutableList<Category>?> {

        return repository.category(id).convert()
    }


}