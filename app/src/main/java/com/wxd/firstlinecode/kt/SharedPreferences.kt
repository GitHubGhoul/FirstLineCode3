package com.wxd.firstlinecode.kt

import android.content.SharedPreferences
import android.media.MediaCodec

fun SharedPreferences.open(block: SharedPreferences.Editor.()->Unit){
    val editor = edit()
    editor.block()
    editor.apply()
}