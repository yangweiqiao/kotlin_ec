package com.niu1078.good.presenter.view

import com.niu1078.base.presenter.view.BaseView
import com.niu1078.good.data.protocol.CartGoods

/**
 * author :ywq .
 * time: 2017/12/30:12:17.
 * desc :购物车 view
 * action:
 */
interface CartListView:BaseView {
fun onDeleteCartListResult(result:Boolean)
fun onSubmitCartListResult(result:Int)
fun onGetCartListResult(result:MutableList<CartGoods>?)


}