package com.hp.kotlincode.lambda

fun main() {
    val list = getMapOf()
    for ((fruit, number) in list) {
        println("fruit is $fruit, number is $number")
    }
}

fun getList(): List<String> {
    val list = ArrayList<String>()
    list.add("Apple")
    list.add("Banana")
    list.add("Orange")
    list.add("Pear")
    list.add("Grape")
    return list
}

fun getListOf(): List<String> {
    return listOf("Apple", "Banana", "Orange", "Pear", "Grape")
}

fun getMutableListOf(): MutableList<String> {
    return mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
}

fun getSetOf(): Set<String> {
    return setOf("Apple", "Banana", "Orange", "Pear", "Grape")
}

fun getMap(): Map<String, Int> {
    val map = HashMap<String, Int>()
    map["Apple"] = 1
    map["Banana"] = 2
    map["Orange"] = 3
    map["Pear"] = 4
    map["Grape"] = 5
    return map
}

fun getMapOf(): Map<String, Int> {
    return mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
}