package com.niu1078.good.data.protocol

/**
 * author :ywq .
 * time: 2017/12/21:15:06.
 * desc :
 * action:
 */
data   class Category(
        val id: Int, //分类ID
        val categoryName: String, //分类名称
        val categoryIcon: String = "", //分类图标
        val parentId: Int, //分类 父级ID
        var isSelected: Boolean = false//是否被选中
)