package com.niu1078.base.widget

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * author :ywq .
 * time: 2017/12/16:15:29.
 * desc :
 * action:
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)

    }
}