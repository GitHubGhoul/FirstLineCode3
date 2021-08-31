package com.wxd.firstlinecode.mutilmedia

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityPlayAudioBinding

class PlayAudioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayAudioBinding
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initMediaPlayer()
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        binding.videoView.setVideoURI(uri)
        binding.play.setOnClickListener {
            /*if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }*/
            if (!binding.videoView.isPlaying) {
                binding.videoView.start() // 开始播放
            }
        }
        binding.pause.setOnClickListener {
            /*if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }*/
            if (binding.videoView.isPlaying) {
                binding.videoView.pause() // 暂停播放
            }
        }
        binding.stop.setOnClickListener {
            /*if (mediaPlayer.isPlaying) {
                mediaPlayer.reset()
                initMediaPlayer()
            }*/
            if (binding.videoView.isPlaying) {
                binding.videoView.resume() // 重新播放
            }
        }
    }

    private fun initMediaPlayer(){
        val assetManager = assets
        val fd = assetManager.openFd("Komorebi.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        binding.videoView.suspend()
    }

}