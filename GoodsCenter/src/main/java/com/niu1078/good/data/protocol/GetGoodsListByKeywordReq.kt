package com.niu1078.good.data.protocol

/**
 * author :ywq .
 * time: 2017/12/25:19:58.
 * desc :
 * action:
 */
/*
    按关键字搜索商品
 */
data class GetGoodsListByKeywordReq(
        val keyword: String,
        val pageNo: Int
)
