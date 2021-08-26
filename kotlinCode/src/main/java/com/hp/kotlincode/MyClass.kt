package com.hp.kotlincode

import com.hp.kotlincode.object_oriented.Cellphone
import com.hp.kotlincode.object_oriented.Singleton
import com.hp.kotlincode.object_oriented.Student
import com.hp.kotlincode.object_oriented.Study

fun main() {
    val student = Student("a123", 5, "Jack", 19)
    doStudy(student)
    val cellphone1 = Cellphone("Samsung", 1299.99)
    val cellphone2 = Cellphone("Samsung", 1299.99)
    println(cellphone1)
    println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))
    Singleton.singletonTest()
}

fun doStudy(study: Study?) {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
