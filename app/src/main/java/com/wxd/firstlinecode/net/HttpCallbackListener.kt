package com.wxd.firstlinecode.net

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}