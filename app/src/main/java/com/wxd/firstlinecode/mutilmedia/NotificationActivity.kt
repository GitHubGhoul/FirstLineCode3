package com.wxd.firstlinecode.mutilmedia

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.broadcast.LoginActivity
import com.wxd.firstlinecode.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        binding.sendNotice.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            val pi = PendingIntent.getActivity(this,0,intent,0)
            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("This is content title")
                //.setContentText("This is content text")
                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, " +
                        "send and sync data, and use voice actions. " +
                        "Get the official Android IDE and developer tools to build apps for Android."))
                /*.setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(resources, R.drawable.big_image)))*/
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.cherry))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1,notification)
        }
    }
}