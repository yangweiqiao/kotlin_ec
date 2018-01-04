package com.niu1078.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.order.R
import com.niu1078.order.data.protocol.Order
import com.niu1078.order.injection.component.DaggerOrderComponent
import com.niu1078.order.injection.module.OrderModule
import com.niu1078.order.presenter.p.OrderPresenter
import com.niu1078.order.presenter.view.OrderView
import com.niu1078.provider.common.ProviderConstant
import com.niu1078.provider.router.RouterPath
import org.jetbrains.anko.toast

/**
 * author :ywq .
 * time: 2018/1/3:17:34.
 * desc :提交订单界面
 * action:
 */


@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM  )
class OrderConfirmActivity : BaseMvpActivity<OrderPresenter>(), OrderView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0


    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)

        ARouter.getInstance().inject(this)
        // val intExtra = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,0)
        //toast("订单id$mOrderId")
        initView()
        initData()
    }

    private fun initData() {

        mPresenter.getOrderById(mOrderId)
    }

    private fun initView() {


    }
    override fun onGetOrderByIdResult(t: Order) {
        toast(t.toString())
    }
}