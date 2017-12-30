package com.niu1078.good.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseActivity
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.base.widget.BannerImageLoader
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.event.AddCartEvent
import com.niu1078.good.event.GoodsDetailImageEvent
import com.niu1078.good.event.SkuChangedEvent
import com.niu1078.good.event.UpdateCartSizeEvent
import com.niu1078.good.injection.component.DaggerGoodsComponent
import com.niu1078.good.injection.module.GoodsModule
import com.niu1078.good.presenter.p.GoodsDetailPresenter
import com.niu1078.good.presenter.view.GoodsDetailView
import com.niu1078.good.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*


/**
 * author :ywq .
 * time: 2017/12/29:16:59.
 * desc :商品详情第一个界面
 * action:商品的基本信息
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {
    override fun onAddCartResult(count: Int) {
        println("购物车数量发生了变化:$count")
        Bus.send(UpdateCartSizeEvent())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnima()
        initPop()
        loadData()
        initObserve()
    }

    private lateinit var mSkuPop: GoodsSkuPopView
    private fun initPop() {

        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {

            (activity as BaseActivity).contentView.startAnimation(scaleAnimationEnd)

        }
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

            mSkuPop.showAtLocation((activity as BaseActivity).contentView,
                    Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0
            )
            (activity as BaseActivity).contentView.startAnimation(scaleAnimationStart)

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
private lateinit var goods:Goods
    @SuppressLint("SetTextI18n")
    private fun loadPopData(result: Goods) {
        goods=result
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)


    }

    private fun initObserve() {
        Bus.observe<SkuChangedEvent>().subscribe {

            mSkuSelectedTv.text = "${mSkuPop.getSelectSku()}      ${mSkuPop.getSelectCount()}件"

        }

                .registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }
                .registerInBus(this)
    }

    private fun addCart() {

        mPresenter.addCart(goods.id,goods.goodsDesc,goods.goodsDefaultIcon,goods.goodsDefaultPrice,mSkuPop.getSelectCount(),mSkuPop.getSelectSku())

    }

    private lateinit var scaleAnimationStart: Animation
    private lateinit var scaleAnimationEnd: Animation
    private fun initAnima() {
        scaleAnimationStart = ScaleAnimation(1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

        scaleAnimationStart.duration = 500
        scaleAnimationStart.fillAfter = true
        scaleAnimationEnd = ScaleAnimation(0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

        scaleAnimationEnd.duration = 500
        scaleAnimationEnd.fillAfter = true
    }


    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}