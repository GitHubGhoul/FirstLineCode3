package com.wxd.firstlinecode.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class VMViewModel(countReserved: Int) : ViewModel() {

    private val userLiveData = MutableLiveData<User>()

    private val userIdLiveData = MutableLiveData<String>()

    private val refreshLiveData = MutableLiveData<Any?>()

    val refreshResult = Transformations.switchMap(refreshLiveData) {
        Repository.refresh()
    }

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it)
    }

    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.firstName} ${it.lastName}"
    }

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    fun refresh() {
        refreshLiveData.value = refreshLiveData.value
    }
}