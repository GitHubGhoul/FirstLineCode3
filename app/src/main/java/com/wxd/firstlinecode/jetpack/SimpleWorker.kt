package com.wxd.firstlinecode.jetpack

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Log.e("SimpleWorker", "doWork: in SimpleWorker")
        return Result.retry()
    }

}