package com.niu1078.base.injection.module

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

/**
 * author :ywq .
 * time: 2017/12/25:16:34.
 * desc :
 * action:
 */
@Module
class FragmentModule (private  val fragment: Fragment){

    @Provides
    fun providesFragment() :Fragment{

        return fragment
    }
}