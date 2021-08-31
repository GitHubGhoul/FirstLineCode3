package com.wxd.firstlinecode.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.wxd.firstlinecode.MainActivity
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.mutilmedia.CameraAlbumActivity
import com.wxd.firstlinecode.mutilmedia.NotificationActivity

class MyService : Service() {

    companion object {
        private const val TAG = "MyService"
    }

    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.e(TAG, "startDownload: ")
        }

        fun getProgress(): Int {
            Log.e(TAG, "getProgress: ")
            return 0
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, CameraAlbumActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.ic_notifications)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.apple))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}