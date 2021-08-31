package com.wxd.kotlincode.generic.contravariant

interface Transformer<in T> {
    fun transform(t: T): String
}