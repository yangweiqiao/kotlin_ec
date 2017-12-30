package com.niu1078.good.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*


/**
 * author :ywq .
 * time: 2017/12/18:14:23.
 * desc :
 * action:
 */
class CaregoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, CaregoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mTopCategoryNameTv.text =dataList[position].categoryName
        holder.itemView.mTopCategoryNameTv.isSelected =dataList[position].isSelected

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    }

}