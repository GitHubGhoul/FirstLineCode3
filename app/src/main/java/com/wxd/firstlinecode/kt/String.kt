package com.wxd.firstlinecode.kt

operator fun String.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}

fun String.timesRepeat(n: Int) = repeat(n)