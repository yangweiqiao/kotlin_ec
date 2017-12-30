package com.niu1078.good.presenter.view

import com.niu1078.base.presenter.view.BaseView
import com.niu1078.good.data.protocol.Goods

/**
 * author :ywq .
 * time: 2017/12/29:16:30.
 * desc :商品详情 view
 * action:
 */
interface GoodsDetailView : BaseView {

    fun onGoodsDetailResult(result: Goods)
    fun onAddCartResult(count:Int)
}