package com.niu1078.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.niu1078.R
import com.niu1078.base.common.BaseConstant
import com.niu1078.base.ext.loadUrl
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.base.widget.BannerImageLoader
import com.niu1078.common.*
import com.niu1078.provider.common.ProviderConstant
import com.niu1078.ui.activity.SettingActivity
import com.niu1078.ui.adapter.HomeDiscountAdapter
import com.niu1078.usercenter.ui.activity.LoginActivity
import com.niu1078.usercenter.ui.activity.UserInfoActivity
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import isLogined
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :
 * action:
 */
class MeFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_me, null)

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        initView()
    }
    private fun initView() {
        //判断用户是否登录

        if (isLogined()) {
            //已经登录
            //设置数据
            mUserIconIv.loadUrl(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            //没有登录
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = "登录/注册"
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()

                }

            }

            R.id.mSettingTv->{

                startActivity<SettingActivity>()

            }

        }

    }
}