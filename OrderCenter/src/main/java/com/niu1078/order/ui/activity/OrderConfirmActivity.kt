package com.niu1078.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.niu1078.base.ui.activity.BaseMvpActivity
import com.niu1078.order.R
import com.niu1078.order.presenter.p.OrderPresenter
import com.niu1078.order.presenter.view.OrderView
import com.niu1078.provider.router.RouterPath

/**
 * author :ywq .
 * time: 2018/1/3:17:34.
 * desc :提交订单界面
 * action:
 */

@Route(path=RouterPath.OrderCenter.path_order_confirm)
class OrderConfirmActivity : BaseMvpActivity<OrderPresenter>(),OrderView {
//    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
//    @JvmField
//    var mOrderId: Int = 0


    override fun injectComponent() {
//        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule())
//                .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
  //      toast("订单id$mOrderId")

        initView()
        initData()
    }

    private fun initData() {


    }

    private fun initView() {


    }

}