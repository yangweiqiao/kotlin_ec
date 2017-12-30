package com.niu1078.good.ui.fragment

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.niu1078.base.ui.fragment.BaseMvpFragment
import com.niu1078.good.R
import com.niu1078.good.data.protocol.Category
import com.niu1078.good.injection.component.DaggerCategoryComponent
import com.niu1078.good.injection.module.CategoryModule
import com.niu1078.good.presenter.p.CategoryPresenter
import com.niu1078.good.presenter.view.CategoryView
import com.niu1078.good.ui.activity.GoodsListActivity
import com.niu1078.good.ui.adapter.CaregoryAdapter
import com.niu1078.good.ui.adapter.SceondCaregoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :分类界面
 * action:主要展示分类界面的数据和点击切换分类效果
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    lateinit var topCaregoryAdapter: CaregoryAdapter
    lateinit var sceondCaregoryAdapter: SceondCaregoryAdapter
    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(fragmentComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun loadData(id: Int = 0) {

        if (id != 0) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
            val loadingView = mMultiStateView.getView(MultiStateView.VIEW_STATE_LOADING)
            val background = loadingView!!.find<View>(R.id.loading_anim_view).background
            background as AnimationDrawable
            background.start()
        }
//延时测试加载过程中的动画效果
        Observable.timer(1, TimeUnit.SECONDS).subscribe({
            mPresenter.category(id)
        })

    }

    private fun initView() {

        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topCaregoryAdapter = CaregoryAdapter(context)
        mTopCategoryRv.adapter = topCaregoryAdapter

        topCaregoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topCaregoryAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }

                loadData(item.id)
                topCaregoryAdapter.notifyDataSetChanged()
            }
        })


        val gridLayoutManager = GridLayoutManager(context, 3)
        mSecondCategoryRv.layoutManager = gridLayoutManager
        sceondCaregoryAdapter = SceondCaregoryAdapter(context)
        mSecondCategoryRv.adapter = sceondCaregoryAdapter
        sceondCaregoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                toast(item.categoryName)
/*
点击条目跳转到商品列表界面 需要传递的参数是商品的分类id
 */
                startActivity<GoodsListActivity>("id" to item.id)

            }
        })


    }


    override fun onCatregoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {

            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topCaregoryAdapter.setData(result)

                loadData(result[0].id)

            } else {
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                sceondCaregoryAdapter.setData(result)
            }


        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }


    }

}