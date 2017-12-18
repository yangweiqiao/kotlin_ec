package com.niu1078.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.niu1078.R
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.base.widget.BannerImageLoader
import com.niu1078.common.*
import com.niu1078.ui.adapter.HomeDiscountAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :
 * action:
 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_home, null)

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initTopic() {
        //话题  画廊
        mTopicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))

        mTopicPager.currentItem = 1

        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f)
                .pagerMargin(-30.0f)
                .spaceSize(0.0f)
                .build()
    }

    private fun initDiscount() {
        val manger = LinearLayoutManager(context)
        manger.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manger
        val disCountAdapter = HomeDiscountAdapter(activity)


        mHomeDiscountRv.adapter = disCountAdapter
        disCountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("窗外开始下起毛毛雨  ", "云遮住了星星 ", "夜深了还没有睡意", "翻来覆去地想你", "时钟嘀嗒嘀嗒的声音",
                "像在说我爱你", "转过两点三点到六点", "恨不得快点见到你", "幸福的距离 ", "就算万公里", "在你眼里有我想要的勇气", "从南极飞到北极", "南京到北京",
                "你的笑胜过那些美景", "我们勾勾手 就一言为定", "我会傻傻地好好地爱你", "你的名加我的姓"))

    }

    private fun initBanner() {
        mHomeBanner.setImages(listOf(HOME_TOPIC_FOUR, HOME_TOPIC_FIVE, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.startAutoPlay()
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }
}