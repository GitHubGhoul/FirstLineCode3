package com.wxd.firstlinecode.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun actionStart() {
        Log.e("MyObserver", "actionStart: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun actionStop() {
        Log.e("MyObserver", "actionStop: ")
    }
}