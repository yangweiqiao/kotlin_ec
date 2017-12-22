package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.ui.adapter.CaregoryAdapter
import com.niu1078.good.ui.adapter.SceondCaregoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :分类界面
 * action:主要展示分类界面的数据和点击切换分类效果
 */
class CategoryFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        mTopCategoryRv.layoutManager = LinearLayoutManager(context)

        val topCaregoryAdapter = CaregoryAdapter(context)

        val arrayListOf = arrayListOf<Category>()

        (1..15).mapTo(arrayListOf) { Category(it, "测试$it", "", 0, false) }



        mTopCategoryRv.adapter = topCaregoryAdapter

        topCaregoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topCaregoryAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topCaregoryAdapter.notifyDataSetChanged()
            }
        })

        topCaregoryAdapter.setData(arrayListOf)
        val gridLayoutManager = GridLayoutManager(context, 3)
        mSecondCategoryRv.layoutManager = gridLayoutManager
        val sceondCaregoryAdapter = SceondCaregoryAdapter(context)
        mSecondCategoryRv.adapter = sceondCaregoryAdapter
        sceondCaregoryAdapter.setOnItemClickListener(object: BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {


            }
        })
        sceondCaregoryAdapter.setData(arrayListOf)
        mMultiStateView.viewState=0
    }


}