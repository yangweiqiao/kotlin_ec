package com.niu1078.good.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niu1078.base.ui.fragment.BaseFragment
import com.niu1078.good.R

/**
 * author :ywq .
 * time: 2017/12/16:15:38.
 * desc :
 * action:
 */
class CartFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_category, null)

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}