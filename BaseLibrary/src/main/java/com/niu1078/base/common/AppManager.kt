package com.niu1078.base.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * author :ywq .
 * time: 2017/12/14:13:19.
 * desc :App管理器
 * action:
 */
class AppManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {

        val instance: AppManager by lazy { AppManager() }

    }

    //入栈
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    //出栈
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    //获取当前栈顶的activity
    fun currentActivity(): Activity {
        //最后一个元素 压栈
        return activityStack.lastElement()
    }

    // 清理所有栈 出栈
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }


@SuppressLint("MissingPermission")
//退出app
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}