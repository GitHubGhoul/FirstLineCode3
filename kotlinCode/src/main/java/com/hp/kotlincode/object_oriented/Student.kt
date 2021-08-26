package com.hp.kotlincode.object_oriented

class Student(val sno: String = "", val grade: Int = 0, name: String = "", age: Int = 0) :
    Person(name, age),Study {

    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }

    override fun readBooks() {
        println(name + " is reading.")
    }

    override fun doHomework() {
        println(name + " is doing homework.")
    }

}