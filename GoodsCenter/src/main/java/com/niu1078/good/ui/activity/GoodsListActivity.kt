package com.niu1078.good.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
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

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView {
    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        println(result)
        result?.let {
            goodsAdapter.setData(result)
        }
    }


    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_list)
         initView()
        initData()

    }

    private fun initData() {

        mPresenter.getGoodsList(intent.getIntExtra("id", 0), 1)
    }

    lateinit var goodsAdapter:GoodsAdapter
      fun initView(){
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
}
