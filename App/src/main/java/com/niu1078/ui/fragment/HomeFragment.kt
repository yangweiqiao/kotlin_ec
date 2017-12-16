package com.niu1078.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niu1078.R
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.base.widget.BannerImageLoader
import com.niu1078.common.*
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :
 * action:
 */
class HomeFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView= inflater.inflate(R.layout.fragment_home,null)
        initView( rootView)
        return rootView

    }
lateinit var mHomeBanner:Banner

    private fun initView(rootView: View) {
        mHomeBanner=rootView.findViewById(R.id.mHomeBanner)
        mHomeBanner
        .setImageLoader(BannerImageLoader())
        mHomeBanner .setImages(listOf(HOME_TOPIC_FOUR, HOME_TOPIC_FIVE, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner .setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner .start()
    }
}