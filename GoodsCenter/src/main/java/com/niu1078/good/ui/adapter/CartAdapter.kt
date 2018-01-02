package com.niu1078.good.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.niu1078.base.ext.loadUrl
import com.niu1078.good.R
import com.niu1078.good.data.protocol.CartGoods
import kotlinx.android.synthetic.main.item_cart_goods.view.*

/**
 * author :ywq .
 * time: 2018/1/2:10:57.
 * desc :
 * action:
 */
class CartAdapter (context: Context) : BaseRecyclerViewAdapter<CartGoods, CartAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_cart_goods, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mGoodsIconIv.loadUrl(dataList[position].goodsIcon)
        holder.itemView.mGoodsDescTv.text =dataList[position].goodsDesc
        holder.itemView.mGoodsSkuTv.text =dataList[position].goodsSku
        holder.itemView.mGoodsPriceTv.text =YuanFenConverter.changeF2YWithUnit( dataList[position].goodsPrice)
        holder.itemView.mGoodsCountBtn.setCurrentNumber(dataList[position].goodsCount)
        holder.itemView.mCheckedCb.isChecked = dataList[position].isSelected

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



    }

}