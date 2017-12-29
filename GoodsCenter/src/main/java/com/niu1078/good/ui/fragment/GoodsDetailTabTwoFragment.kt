package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.niu1078.base.ext.loadUrl
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.event.GoodsDetailImageEvent
import com.niu1078.good.presenter.p.GoodsDetailPresenter
import com.niu1078.good.presenter.view.GoodsDetailView
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * author :ywq .
 * time: 2017/12/29:16:59.
 * desc :
 * action:
 */
class GoodsDetailTabTwoFragment : BaseFragment () {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initview()

    }

    private fun initview() {
Bus.observe<GoodsDetailImageEvent>()
        .subscribe {
t:GoodsDetailImageEvent ->
            run {
                mGoodsDetailOneIv.loadUrl(t.imageOne)
                mGoodsDetailOneIv.loadUrl(t.imageTwo)
            }
        }
        .registerInBus(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}