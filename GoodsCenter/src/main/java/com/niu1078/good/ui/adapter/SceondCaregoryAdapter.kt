package com.niu1078.good.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.niu1078.base.ext.loadUrl
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Category
import kotlinx.android.synthetic.main.layout_second_category_item.view.*


/**
 * author :ywq .
 * time: 2017/12/18:14:23.
 * desc :
 * action:
 */
class SceondCaregoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, SceondCaregoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_second_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]
       holder.itemView.mCategoryIconIv.loadUrl(category.categoryIcon)
       holder.itemView.mSecondCategoryNameTv.text = category.categoryName

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    }

}