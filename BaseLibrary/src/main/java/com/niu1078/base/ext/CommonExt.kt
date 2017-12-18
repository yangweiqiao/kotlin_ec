package com.niu1078.base.ext

import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.niu1078.base.R
import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.base.rx.BaseFunBoolean
import com.niu1078.base.rx.BaseFunData
import com.niu1078.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColorResource
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
fun TextView.enable(et:EditText,method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
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
