package com.wxd.kotlincode.entrust

class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet{

    fun helloWorld() = println("Hello World")
    
    override fun isEmpty() = false

}