package com.wxd.firstlinecode.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService:IntentService("MyIntentService") {
    
    override fun onHandleIntent(intent: Intent?) {
        // 打印当前线程的id
        Log.e("MyIntentService", "Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MyIntentService", "onDestroy executed")
    }

}