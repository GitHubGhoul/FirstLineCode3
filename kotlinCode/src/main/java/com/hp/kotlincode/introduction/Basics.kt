package com.hp.kotlincode.introduction

import kotlin.math.max

fun main(){
    val a = 37
    val b = 40
    val value = largerNumber(a, b)
    println("larger number is $value")
}

fun largerNumber(num1: Int, num2: Int): Int = max(num1, num2)