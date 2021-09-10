package com.wxd.firstlinecode.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VMViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VMViewModel(countReserved) as T
    }
}