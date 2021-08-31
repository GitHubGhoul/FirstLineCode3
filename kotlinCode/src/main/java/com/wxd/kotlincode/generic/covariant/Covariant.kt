package com.wxd.kotlincode.generic.covariant

import com.wxd.kotlincode.generic.Person
import com.wxd.kotlincode.generic.SimpleData
import com.wxd.kotlincode.generic.Student

fun main() {
    val student = Student("Tom", 19)
    val data = SimpleData<Student>(student)
    handleMyData(data)
    val studentData = data.get()
}

fun handleMyData(data: SimpleData<Person>) {
    val personData = data.get()
}