package com.wxd.kotlincode.function

fun main() {
    val count = "ABC123xyz!@#".lettersCount()
    println(count)
}

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}
