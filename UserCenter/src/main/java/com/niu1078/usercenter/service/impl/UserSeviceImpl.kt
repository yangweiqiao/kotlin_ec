package com.niu1078.usercenter.service.impl

import com.niu1078.base.data.protocol.BaseResp
import com.niu1078.base.rx.BaseException
import com.niu1078.usercenter.data.repository.UserRepository
import com.niu1078.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1

/**
 * Created by Administrator on 2017/12/12.
 */
class UserSeviceImpl : UserService {
    override fun register(mobile: String, password: String, verifyCode: String): Observable<Boolean> {
        val repository = UserRepository()//这个里面有一个请求的方法 我们在这里调用

        return    repository.register(mobile, password, verifyCode)
                .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
                    override fun call(t: BaseResp<String>): Observable<Boolean> {
                        return if (t.status == 0) {
                            Observable.just(true)
                        } else {
                            Observable.error(BaseException(t.status,t.message))
                        }

                    }}
                    )



                }

//    override fun register(username: String, password: String): Observable<Boolean> {
//
//        return Observable.just(true)
//    }

    }