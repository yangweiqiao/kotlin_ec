package com.niu1078.base.data.net

import com.niu1078.base.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 单例的工厂模式
 * Created by Administrator on 2017/12/12.
 */
class RetrofitFactory private constructor() {
    //1.私有化构造
    //2.伴生对象,相当于java里面的静态
    companion object {
        //创建对象 java里面会判断是null 加锁 ... kotlin里面by lazy 本身就是一个现场安全的
        val instance: RetrofitFactory by lazy { RetrofitFactory() }

    }

    private val retrofit: Retrofit
    private val intereceptor: Interceptor

    init {
        intereceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                    // .addHeader("","")
                    .build()
            chain.proceed(request)


        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVICE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /**
     * 构建OKHttpClient
     */
    private fun initClient(): OkHttpClient? {

        return OkHttpClient.Builder()

                .addInterceptor(intereceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 构建日志拦截器
     */
    private fun initLogInterceptor(): Interceptor {
        val logInterceptor = HttpLoggingInterceptor()
//设置日志级别
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    /**
     * 创建具体的service
     */
    fun <T> creat(service: Class<T>): T {
        return retrofit.create(service)

    }

}