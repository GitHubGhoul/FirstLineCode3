package com.wxd.kotlincode.introduction

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}
