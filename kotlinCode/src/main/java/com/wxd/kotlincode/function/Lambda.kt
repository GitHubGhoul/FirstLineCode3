package com.wxd.kotlincode.function

import com.wxd.kotlincode.introduction.getMutableListOf

fun main() {
    val list = getMutableListOf()
    val maxLengthFruit = list.maxByOrNull { it.length }
    println("max length fruit is $maxLengthFruit")
    val mapList = list.map { it.toUpperCase() }
    for (fruit in mapList) {
        //println(fruit)
    }
    val filterMapList = list.filter { it.length <= 5 }
        .map { it.toUpperCase() }
    for (fruit in filterMapList) {
        println(fruit)
    }
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult, allResult is $allResult")
}