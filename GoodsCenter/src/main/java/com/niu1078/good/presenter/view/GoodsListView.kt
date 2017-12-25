package com.niu1078.good.presenter.view

import com.niu1078.base.presenter.view.BaseView
import com.niu1078.good.data.protocol.Goods

/**
 * author :ywq .
 * time: 2017/12/25:19:28.
 * desc :
 * action:
 */
interface GoodsListView:BaseView {

    fun onGetGoodsListResult(result:MutableList<Goods>?)
}