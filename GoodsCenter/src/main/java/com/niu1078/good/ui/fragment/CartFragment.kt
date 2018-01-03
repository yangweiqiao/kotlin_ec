package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.YuanFenConverter
import com.niu1078.base.ext.*
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.good.R
import com.niu1078.good.common.GoodsConstant
import com.niu1078.good.data.protocol.CartGoods
import com.niu1078.good.event.CartAllChecked
import com.niu1078.good.event.GoodsDetailImageEvent
import com.niu1078.good.event.UpdateCartSizeEvent
import com.niu1078.good.event.UpdateTotalPriceEvent
import com.niu1078.good.injection.component.DaggerCartComponent
import com.niu1078.good.injection.module.CartModule
import com.niu1078.good.presenter.p.CartListPresenter
import com.niu1078.good.presenter.view.CartListView
import com.niu1078.good.ui.activity.GoodsDetailActivity
import com.niu1078.good.ui.adapter.CartAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :购物车
 * action:
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {
    private lateinit var cartAdapter: CartAdapter

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(fragmentComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initobserve()
        initView()
    }

    override fun onStart() {
        super.onStart()
        initDatas()

    }
    private fun initDatas() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()

    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        cartAdapter = CartAdapter(context)
        mCartGoodsRv.adapter = cartAdapter


        mAllCheckedCb.onClick {
            for (cartGoods in cartAdapter.dataList) {
                cartGoods.isSelected = mAllCheckedCb.isChecked
            }
            updateTotalPrice()
            cartAdapter.notifyDataSetChanged()

        }

        mHeaderBar.getRightView().onClick {

            refreshEditStatus()
        }


        //删除购物车
        mDeleteBtn.onClick {
            val list :MutableList<Int> = arrayListOf()
            cartAdapter.dataList.filter { it.isSelected }.mapTo(list) { it.id }

            if (list.size==0)   myToast("请选择要删除的商品") else mPresenter.deleteCartList(list)

        }

        //提交购物车
        mSettleAccountsBtn.onClick {
            val list :MutableList<CartGoods> = arrayListOf()
            val sum = cartAdapter.dataList.filter { it.isSelected }
                    .mapTo(list) { it }
                    .map { it.goodsCount * it.goodsPrice }
                    .sum()
            if (list.size==0)   myToast("请选择要结算的商品") else   mPresenter.submitCart(list,sum)
        }
    }

    private fun refreshEditStatus() {
        //获取文字判断当前的状态是什么状态
        //是不是正在编辑的状态
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()

        mTotalPriceTv.isGone(isEditStatus)
        mSettleAccountsBtn.isGone(isEditStatus)
        mDeleteBtn.isGone(!isEditStatus)
        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete )else getString(R.string.common_edit )
    }


    private fun initobserve() {
        //这个用着感觉比eventBus爽
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    /*
                       这有个问题  不知道为什么会接受2次事件
                     */
              //      mPresenter.getCartList()
                }
                .registerInBus(this)


        //通过adapter里面发送的event  来判断当前的状态是什么
        Bus.observe<CartAllChecked>()
                .subscribe { t: CartAllChecked ->
                    run {
                        mAllCheckedCb.isChecked = t.isChecked
                        updateTotalPrice()
                    }
                }
                .registerInBus(this)
//数量影响总价
        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }
                .registerInBus(this)

    }

    //界面销毁的时候
    // 注意的是bus事件的销毁
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    override fun onDeleteCartListResult(result: Boolean) {

        myToast("删除成功")
        initDatas()
    }

    override fun onSubmitCartListResult(result: Int) {

        myToast("提交成功$result")
        initDatas()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        result?.let {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            cartAdapter.setData(result)

        }
        if (result==null ||result.size==0  ) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        mAllCheckedCb.isChecked=false
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size?:0)
        Bus.send(UpdateCartSizeEvent())
        updateTotalPrice()
    }

    private var mTotalPrice: Long = 0
    fun updateTotalPrice() {
        mTotalPrice = cartAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)} "

    }


}