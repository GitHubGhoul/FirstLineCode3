package com.hp.kotlincode.introduction

fun main() {
    val a = 37
    val b = 40
    val value = largerNumberIf(a, b)
    println("larger number is $value")

    val num = 10L
    checkNumber(num)
}

fun largerNumberIf(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun getScoreIf(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    77
} else if (name == "Jack") {
    95
} else if (name == "Lily") {
    100
} else {
    0
}

fun getScoreWhen(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

fun getScoreWhenNoParam(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

fun forIn(){
    for (i in 0..10) {
        println(i)
    }
    for (i in 10 downTo 1) {
        println(i)
    }
}


fun forUtil(){
    for (i in 0 until 10 step 2) {
        println(i)
    }
}


