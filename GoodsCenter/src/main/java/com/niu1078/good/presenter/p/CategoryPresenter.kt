package com.niu1078.good.presenter.p

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.presenter.view.CategoryView
import com.niu1078.good.service.CategoryService
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/25:16:20.
 * desc :分类 Presenter
 * action:
 */
class CategoryPresenter @Inject constructor():BasePresenter<CategoryView>() {


    @Inject
    lateinit var categoryService: CategoryService
    fun category(id :Int){

        mView.showLoading()
        categoryService.category(id)
                .excute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                            mView.onCatregoryResult(t)
                    }
                }, lifecycleProvider)
    }


}