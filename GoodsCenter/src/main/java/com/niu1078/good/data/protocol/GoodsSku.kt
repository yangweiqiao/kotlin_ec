package com.niu1078.good.data.protocol

/**
 * author :ywq .
 * time: 2017/12/25:19:57.
 * desc :
 * action:
 */
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)

