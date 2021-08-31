package com.wxd.kotlincode.generic.contravariant

import com.wxd.kotlincode.generic.Person
import com.wxd.kotlincode.generic.Student

fun main() {
    val trans = object : Transformer<Person> {
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    handleTransformer(trans)
}

fun handleTransformer(trans: Transformer<Student>) {
    val student = Student("Tom", 19)
    val result = trans.transform(student)
}
