package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import com.niu1078.base.ext.myToast
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.base.widget.BannerImageLoader
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.data.protocol.GoodsSku
import com.niu1078.good.event.GoodsDetailImageEvent
import com.niu1078.good.injection.component.DaggerGoodsComponent
import com.niu1078.good.injection.module.GoodsModule
import com.niu1078.good.presenter.p.GoodsDetailPresenter
import com.niu1078.good.presenter.view.GoodsDetailView
import com.niu1078.good.ui.activity.GoodsDetailActivity
import com.niu1078.good.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_goods_detail.*
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.contentView


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


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initPop()
        loadData()

    }
private  lateinit var mSkuPop: GoodsSkuPopView
    private fun initPop() {

        mSkuPop = GoodsSkuPopView(activity)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(fragmentComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {

            mSkuPop.showAtLocation((activity as BaseActivity).contentView ,
                    Gravity.BOTTOM and  Gravity.CENTER_HORIZONTAL,0,0
                    )
        }
    }

    fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra("id", -1))
    }



    override fun onGoodsDetailResult(result: Goods) {


        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()
        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mGoodsPriceTv.text = result.goodsDefaultSku
        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    private fun loadPopData(result: Goods) {

        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)
    }
}