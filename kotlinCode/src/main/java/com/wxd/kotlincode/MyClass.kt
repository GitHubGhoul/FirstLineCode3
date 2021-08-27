package com.wxd.kotlincode

import com.wxd.kotlincode.function.doSomething
import com.wxd.kotlincode.object_oriented.Cellphone
import com.wxd.kotlincode.object_oriented.Singleton
import com.wxd.kotlincode.object_oriented.Student
import com.wxd.kotlincode.object_oriented.Study

fun main() {
    val student = Student("a123", 5, "Jack", 19)
    doStudy(student)
    val cellphone1 = Cellphone("Samsung", 1299.99)
    val cellphone2 = Cellphone("Samsung", 1299.99)
    println(cellphone1)
    println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))
    Singleton.singletonTest()
    doSomething()
}

fun doStudy(study: Study?) {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
