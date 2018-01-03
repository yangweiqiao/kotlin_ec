package com.niu1078.good.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.DefaultTextWatcher
import com.kotlin.goods.getEditText
import com.niu1078.base.ext.loadUrl
import com.niu1078.base.ext.onClick
import com.niu1078.good.R
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.event.CartAllChecked
import com.niu1078.good.event.UpdateTotalPriceEvent
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
        val cartGoods = dataList[position]
        holder.itemView.mGoodsIconIv.loadUrl(cartGoods.goodsIcon)
        holder.itemView.mGoodsDescTv.text = cartGoods.goodsDesc
        holder.itemView.mGoodsSkuTv.text = cartGoods.goodsSku
        holder.itemView.mGoodsPriceTv.text =YuanFenConverter.changeF2YWithUnit( cartGoods.goodsPrice)
        holder.itemView.mGoodsCountBtn.setCurrentNumber(cartGoods.goodsCount)
        holder.itemView.mCheckedCb.isChecked = cartGoods.isSelected
//点击选择框
        holder.itemView.mCheckedCb.onClick {
            cartGoods.isSelected = holder.itemView.mCheckedCb.isChecked

            //点击之后的状态已经发生变化了
            val isAllChecked = dataList.all {  it.isSelected }
            Bus.send(CartAllChecked(isAllChecked))
            notifyDataSetChanged()
        }


        //输入数量
        holder.itemView.mGoodsCountBtn.getEditText()
                .addTextChangedListener(object :DefaultTextWatcher(){
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        super.onTextChanged(s, start, before, count)
                        cartGoods.goodsCount = s.toString().toInt()
                        Bus.send(UpdateTotalPriceEvent())
                    }
                })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

}