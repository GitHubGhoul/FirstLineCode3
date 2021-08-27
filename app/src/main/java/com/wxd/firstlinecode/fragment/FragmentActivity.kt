package com.wxd.firstlinecode.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wxd.firstlinecode.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}