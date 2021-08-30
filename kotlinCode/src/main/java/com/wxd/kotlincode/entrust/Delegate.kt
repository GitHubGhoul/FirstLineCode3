package com.wxd.kotlincode.entrust

import kotlin.reflect.KProperty

class Delegate {

    var propValue: Any? = null
    
    operator fun getValue(myClass: MyClass, prop: KProperty<*>): Any? {
        return propValue
    }
    operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
        propValue = value
    }

}