package com.wxd.firstlinecode.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.e("onReceive", "AnotherBroadcastReceiver: ")
        Toast.makeText(context, "received in AnotherBroadcastReceiver",
            Toast.LENGTH_SHORT).show()
    }
}