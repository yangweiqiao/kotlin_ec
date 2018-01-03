package com.niu1078.base.ext

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.niu1078.base.R
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.base.rx.BaseFunBoolean
import com.niu1078.base.rx.BaseFunData
import com.niu1078.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Administrator on 2017/12/12.
 * 扩展方法
 */


fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {


    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

//扩展点击事件一
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

//扩展点击事件二
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

//扩展flatMap
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunData())
}

/*
 扩展Boolean类型数据转换
*/
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFunBoolean())
}

//    //扩展flatMap
//    fun <T> Observable<BaseResp<T>>.convertBooelan(): Observable<Boolean> {
//        return this.flatMap(BaseFunBoolean())
//    }


//fun Button.enable(et: EditText, method: () -> Boolean) {
//    val textview = this
//    et.addTextChangedListener(object : DefaultTextWatcher() {
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            println("输入改变")
//            textview.isEnabled = method()
//        }
//    })
//}
/*
    扩展TextView可用性
 */
fun TextView.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })

}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/*
    toast的扩展方法
 */
fun Context.myToast(msg: String) {

    runOnUiThread {
        toast(msg)
    }

}
/*
    toast的扩展方法
 */
fun Fragment.myToast(msg: String) {
    this.activity.runOnUiThread {
        activity. toast(msg)
    }


}
/*
    控件是不是可见
 */
/**
 * true 表示隐藏
 */
fun View.isGone(boolean: Boolean) {
    if (boolean) {
        //隐藏
        this.visibility = View.GONE
    } else {
        //可见
        this.visibility = View.VISIBLE
    }

}



/*
    控件是不是可见
 */
fun View.isVisibility(boolean: Boolean) {
    if (boolean) {
        //可见
        this.visibility = View.VISIBLE
    } else {
        //不可见
        this.visibility = View.INVISIBLE
    }

}


/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}