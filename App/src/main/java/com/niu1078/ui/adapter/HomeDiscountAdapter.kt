package com.niu1078.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.GlideUtils
import com.niu1078.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * author :ywq .
 * time: 2017/12/18:14:23.
 * desc :
 * action:
 */
class HomeDiscountAdapter(context: Context) : BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext, dataList.get(position), holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "$123.00"
        holder.itemView.mDiscountBeforeTv.text = "$1230.00"

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }

    }

}