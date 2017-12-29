package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.injection.component.DaggerGoodsComponent
import com.niu1078.good.injection.module.GoodsModule
import com.niu1078.good.presenter.p.GoodsDetailPresenter
import com.niu1078.good.presenter.view.GoodsDetailView

/**
 * author :ywq .
 * time: 2017/12/29:16:59.
 * desc :
 * action:
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onGoodsDetailResult(result: Goods) {

    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(fragmentComponent)  .goodsModule(GoodsModule()).build().inject(this)
    mPresenter.mView = this

    }
}