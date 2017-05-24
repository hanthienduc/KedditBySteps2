package com.hanthienduc.kedditbysteps.commons

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by hanth on 23/05/2017.
 */

open class RxBaseFragment() : Fragment(){
    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()

        subscriptions.clear()
    }
}