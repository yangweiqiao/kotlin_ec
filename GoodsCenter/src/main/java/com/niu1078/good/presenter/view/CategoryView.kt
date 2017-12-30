package com.niu1078.good.presenter.view

import com.niu1078.base.presenter.view.BaseView
import com.niu1078.good.data.protocol.Category

/**
 * author :ywq .
 * time: 2017/12/25:16:19.
 * desc :分类 view
 * action:
 */
interface CategoryView : BaseView {
    /**
     * 定义一个分类界面数据回调的方法啊
     */
  fun  onCatregoryResult(result: MutableList<Category>?)


}