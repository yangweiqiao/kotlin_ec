package com.niu1078.good.data.protocol

/**
 * author :ywq .
 * time: 2017/12/21:15:07.
 * desc :
 * action:
 */
/*
    获取分类列表请求，parentId为0是一级分类
 */
data class GetCategoryReq (val parentId: Int)
