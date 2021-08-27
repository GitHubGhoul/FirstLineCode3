package com.wxd.firstlinecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wxd.firstlinecode.activity.BaseActivity
import com.wxd.firstlinecode.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.forceOffline.setOnClickListener {
            val intent = Intent("com.wxd.broadcastbestpractice.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
    }
}