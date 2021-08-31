package com.wxd.kotlincode.function

fun main() {
    val result1 = getGenericType<String>()
    val result2 = getGenericType<Int>()
    println("result1 is $result1")
    println("result2 is $result2")
}

inline fun <reified T> getGenericType() = T::class.java
