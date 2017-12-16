package com.niu1078.usercenter.presenter.p

import com.niu1078.base.ext.excute
import com.niu1078.base.presenter.BasePresenter
import com.niu1078.base.rx.BaseSubscriber
import com.niu1078.usercenter.presenter.view.UserInfoView
import com.niu1078.usercenter.service.UploadService
import com.niu1078.usercenter.service.UserService
import javax.inject.Inject

/**
 * author :ywq .
 * time: 2017/12/16:12:49.
 * desc :
 * action:
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        uploadService.getUploadToken().excute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {

                mView.onGetUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }


}