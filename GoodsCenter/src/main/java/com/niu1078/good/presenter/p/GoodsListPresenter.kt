package com.niu1078.good.presenter.p

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.data.repository.GoodsRepository
import com.niu1078.good.presenter.view.GoodsListView
import com.niu1078.good.service.GoodsService
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/25:19:27.
 * desc :
 * action:
 */
class GoodsListPresenter @Inject constructor():BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodsService: GoodsService
    /*
          获取商品列表
       */
    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId,pageNo).excute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        }, lifecycleProvider)

    }


    fun getGoodsListByKeyWord(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword,pageNo).excute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        }, lifecycleProvider)

    }

}