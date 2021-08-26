package com.hp.kotlincode.object_oriented

import sun.management.Agent

open class Person(val name: String, val age: Int) {

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }

}