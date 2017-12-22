package com.niu1078.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME
/**
 * author :ywq .
 * time: 2017/12/14:10:39.
 * desc :作用域
 * action:
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope