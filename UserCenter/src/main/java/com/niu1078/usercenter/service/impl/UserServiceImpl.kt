package com.niu1078.usercenter.service.impl

import com.kotlin.user.data.protocol.UserInfo
import com.niu1078.base.ext.convert
import com.niu1078.usercenter.data.repository.UserRepository
import com.niu1078.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject
import com.niu1078.base.ext.convertBoolean

/**
 * Created by Administrator on 2017/12/12.
 */
class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var repository: UserRepository
    /**
     * login
     */
    override fun login(mobile: String, password: String ,pushId:String): Observable<UserInfo> {

        return repository.login(mobile, password,pushId)
                .convert()
    }


    /**
     * register
     */
    override fun register(mobile: String, password: String, verifyCode: String): Observable<Boolean> {

        return repository.register(mobile, password, verifyCode)
                .convertBoolean()

    }
    /**
     * 忘记密码
     */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {

        return repository.forgetPwd(mobile,  verifyCode)
                .convertBoolean()

    }
    /**
     * 重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {

        return repository.resetPwd(mobile,  pwd)
                .convertBoolean()

    }
}