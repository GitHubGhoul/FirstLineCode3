package com.wxd.kotlincode.object_oriented

class Money(val value: Int) {

    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int):Money{
        val sum = newValue + value
        return Money(sum)
    }
}