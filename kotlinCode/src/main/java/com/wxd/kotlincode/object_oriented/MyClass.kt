package com.wxd.kotlincode.object_oriented

class MyClass<T> {

    fun method(param: T): T {
        return param
    }

    fun <S> method1(param: S): S {
        return param
    }

    fun <R : Number> method2(param: R): R {
        return param
    }
}