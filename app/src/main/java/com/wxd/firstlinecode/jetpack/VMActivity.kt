package com.wxd.firstlinecode.jetpack

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityVmactivityBinding
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class VMActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVmactivityBinding
    private lateinit var viewModel: VMViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(MyObserver())
        sp = getPreferences(MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel =
            ViewModelProvider(this, VMViewModelFactory(countReserved)).get(VMViewModel::class.java)
        binding.add.setOnClickListener {
            viewModel.plusOne()
        }
        binding.clear.setOnClickListener {
            viewModel.clear()
        }
        binding.user.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.counter.observe(this) {
            binding.show.text = it.toString()
        }
        viewModel.user.observe(this) {
            binding.show.text = it.firstName
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        binding.addData.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        binding.updateData.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        binding.deleteData.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        binding.queryData.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.e(VMActivity::class.java.name, user.toString())
                }
            }
        }
        //单次后台执行任务
        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .addTag("simple")
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS).build()
        //周期性任务
        //val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
        binding.doWork.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    Log.e("WorkManager", "SUCCEEDED: ")
                } else if (it.state == WorkInfo.State.FAILED) {
                    Log.e("WorkManager", "FAILED: ")
                }
            }
    }

    override fun onPause() {
        super.onPause()
        WorkManager.getInstance(this).cancelAllWork()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}