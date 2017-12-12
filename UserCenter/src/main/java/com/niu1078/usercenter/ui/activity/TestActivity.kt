//package com.niu1078.usercenter.ui.activity
//
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import org.jetbrains.anko.*
//import org.jetbrains.anko.sdk25.coroutines.onClick
//
//class TestActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        verticalLayout {
//            padding = 30
//            editText {
//                hint = "name"
//                textSize = 24f
//            }
//         val name=   editText {
//
//                hint = "password"
//                textSize = 24f
//            }
//            button {
//                text = "点击"
//                onClick {
//                    toast("点击事件${name.text.toString().trim()}")
//                }
//            }
//        }
//
//        toast("${intent.getIntExtra("id", -1)}")
//    }
//}
