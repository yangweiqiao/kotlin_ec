package com.niu1078.good.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.niu1078.base.common.BaseApplication.Companion.context
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Goods
import com.niu1078.good.injection.component.DaggerGoodsComponent
import com.niu1078.good.presenter.p.GoodsListPresenter
import com.niu1078.good.presenter.view.GoodsListView
import com.niu1078.good.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods_list.*
import org.jetbrains.anko.toast

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {
    lateinit var goodsAdapter: GoodsAdapter
    var mCurrentPage: Int = 1
    var mMaxPage: Int = 2

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_list)
        initView()
        initRefreshLayout()
        initData()

    }

    private fun initRefreshLayout() {
mBGARefreshLayout.setDelegate(this)
        val bgaRefreshViewHolder = BGANormalRefreshViewHolder(this, true)
        bgaRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        bgaRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mBGARefreshLayout.setRefreshViewHolder(bgaRefreshViewHolder)

    }

    private fun initData() {

        mPresenter.getGoodsList(intent.getIntExtra("id", 0), mCurrentPage)
    }
    private fun initDatabyKeyWord() {
        mPresenter.getGoodsListByKeyWord("", mCurrentPage)
    }


    fun initView() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        mGoodsRecyclerView.layoutManager = gridLayoutManager
        goodsAdapter = GoodsAdapter(context)
        mGoodsRecyclerView.adapter = goodsAdapter
        goodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                toast(item.goodsDesc)


            }
        })

    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
mBGARefreshLayout.endLoadingMore()
        mBGARefreshLayout.endRefreshing()
        result?.let {

           mMaxPage= result[0].maxPage
            if (mCurrentPage!=1){
             goodsAdapter.dataList.addAll(result!!)
                goodsAdapter.setData(goodsAdapter.dataList)
            }else{
                goodsAdapter.setData(result)
            }
        }


    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage >= mMaxPage) {
            false
        } else {
            mCurrentPage++
            initData()
            true
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage=1
        initData()
    }

}
