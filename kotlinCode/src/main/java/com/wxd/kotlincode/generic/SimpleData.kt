package com.wxd.kotlincode.generic

class SimpleData<out T>(val data: T?) {
    fun get(): T? {
        return data
    }
}
