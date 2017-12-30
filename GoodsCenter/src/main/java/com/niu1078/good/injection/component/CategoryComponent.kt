package com.niu1078.good.injection.component

import com.niu1078.base.injection.Component.ActivityComponent
import com.niu1078.base.injection.PerComponentScope
import com.niu1078.good.injection.module.CategoryModule
import com.niu1078.good.ui.fragment.CategoryFragment
import dagger.Component

/**
 * author :ywq .
 * time: 2017/12/25:16:32.
 * desc :分类component
 * action:
 */
@PerComponentScope
@Component(modules = arrayOf(CategoryModule::class) ,dependencies = arrayOf(ActivityComponent::class))
interface CategoryComponent {
    fun inject(fragment:  CategoryFragment)

}