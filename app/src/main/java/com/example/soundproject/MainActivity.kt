package com.example.soundproject

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var assetManager: AssetManager

    private lateinit var catIV: ImageView
    private lateinit var chickenIV: ImageView
    private lateinit var dogIV: ImageView
    private lateinit var elephantIV: ImageView
    private lateinit var pigIV: ImageView
    private lateinit var sheepIV: ImageView



    private var catSound: Int = 0
    private var chickenSound: Int = 0
    private var dogSound: Int = 0
    private var elephantSound: Int = 0
    private var pigSound: Int = 0
    private var sheepSound: Int = 0

    private var streamID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()

        assetManager = assets
        catSound = loadSound("cat.mp3")
        chickenSound = loadSound("chicken.mp3")
        dogSound = loadSound("dog.mp3")
        elephantSound = loadSound("elephant.mp3")
        sheepSound = loadSound("sheep.mp3")
        pigSound = loadSound("pig.mp3")

        catIV.setOnClickListener { playSound(catSound) }
        chickenIV.setOnClickListener { playSound(chickenSound) }
        dogIV.setOnClickListener { playSound(dogSound) }
        elephantIV.setOnClickListener { playSound(elephantSound) }
        sheepIV.setOnClickListener { playSound(sheepSound) }
        pigIV.setOnClickListener { playSound(pigSound) }
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    private fun playSound(sound: Int): Int {
        if (sound > 0) {
            streamID = soundPool.play(sound, 1F, 1F, 1, 0, 1F)
        }
        return streamID
    }

    private fun loadSound(fileName: String): Int {
        val afd: AssetFileDescriptor = try {
            application.assets.openFd(fileName)
        } catch (e: IOException) {
            Log.d("Alert","Can't find this file")
             return -1
        }
        return  soundPool.load( afd, 1)
    }

    private fun initView() {
        catIV = findViewById(R.id.catIV)
        chickenIV = findViewById(R.id.chickenIV)
        dogIV = findViewById(R.id.dogIV)
        elephantIV = findViewById(R.id.elephantIV)
        pigIV = findViewById(R.id.pigIV)
        sheepIV = findViewById(R.id.sheepIV)
    }
}