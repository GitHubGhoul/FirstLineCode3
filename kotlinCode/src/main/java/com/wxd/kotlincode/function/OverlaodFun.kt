package com.wxd.kotlincode.function

import com.wxd.kotlincode.object_oriented.Money

fun main() {
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money4.value)
}