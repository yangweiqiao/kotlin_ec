package com.niu1078.good.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.niu1078.base.ext.loadUrl
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.data.protocol.Goods
import kotlinx.android.synthetic.main.item_goods.view.*
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * author :ywq .
 * time: 2017/12/25:20:15.
 * desc :
 * action:
 */
class GoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_goods, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val goods = dataList[position]


        holder.itemView.IvGoodsImg.loadUrl(goods.goodsDefaultIcon)
        holder.itemView.tvName.text = goods.goodsDesc
        holder.itemView.tvPrice.text =YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)
        holder.itemView.tvXiaoLiang.text = "销量:${goods.goodsSalesCount}"
        holder.itemView.tvKuCun.text = "库存:${goods.goodsStockCount}"

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    }

}