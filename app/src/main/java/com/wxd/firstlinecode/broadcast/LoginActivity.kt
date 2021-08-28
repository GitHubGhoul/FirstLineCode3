package com.wxd.firstlinecode.broadcast

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wxd.firstlinecode.MainActivity
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.activity.BaseActivity
import com.wxd.firstlinecode.databinding.ActivityLoginBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = load()
        if(user.isNotEmpty()){
            binding.username.setText(user)
            binding.username.setSelection(user.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
        binding.login.setOnClickListener {
            val account = binding.username.text.toString()
            val password = binding.password.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456") {
                save(account)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this, "account or password is invalid",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun save(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return content.toString()
    }
}