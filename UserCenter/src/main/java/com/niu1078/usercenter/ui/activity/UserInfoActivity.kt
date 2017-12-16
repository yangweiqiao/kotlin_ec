package com.niu1078.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.user.utils.UserPrefsUtils
import com.niu1078.base.common.BaseConstant
import com.niu1078.base.ext.onClick
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.provider.common.ProviderConstant
import com.niu1078.usercenter.R
import com.niu1078.usercenter.injection.component.DaggerUserComponent
import com.niu1078.usercenter.injection.module.USerModule
import com.niu1078.usercenter.presenter.p.UserInfoPresenter
import com.niu1078.usercenter.presenter.view.UserInfoView
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.image
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

/**
 * author :ywq .
 * time: 2017/12/16:12:48.
 * desc :
 * action:
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private var mLoacalFile: String? = null
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).uSerModule(USerModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        initView()
        mTakePhoto.onCreate(savedInstanceState)
    }

    private fun initView() {
//
        mHeaderBar.getRightView().onClick {
            toast("保存 ")

        }
        mUserMobileTv.setText(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE))


        //头像
        mUserIconIv.onClick {
            //弹出选择对话框
            showAlertView()
        }

    }

    private fun showAlertView() {
        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet,
                OnItemClickListener { _, position ->
                    //启动压缩图片 配置是默认的
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                    when (position) {
                        0 -> {
                            creatTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }
        ).show()

    }

    //图片获取成功
    override fun takeSuccess(result: TResult?) {
        val image = result?.image

        toast("图片选择成功 ")
        println("图片地址 :${image?.originalPath}")
        println("图片压缩地址 :${image?.compressPath}")
        mLoacalFile = image?.compressPath
        mPresenter.getUploadToken()

    }

    override fun onGetUploadTokenResult(result: String) {
        toast(result)
        mUploadManager.put(mLoacalFile, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                val get = response?.get("hash") as String
                val s = BaseConstant.IMAGE_SERVER_ADDRESS + get
                GlideUtils.loadUrlImage(this@UserInfoActivity, s, mUserIconIv)
                println("图片远程地址:$s")
            }
        }, null)

    }

    //图片获取取消
    override fun takeCancel() {

    }

    //图片获取失败
    override fun takeFail(result: TResult?, msg: String?) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }


    fun creatTempFile() {

        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

}