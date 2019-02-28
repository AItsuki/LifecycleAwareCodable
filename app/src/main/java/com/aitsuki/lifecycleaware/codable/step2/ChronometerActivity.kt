package com.aitsuki.lifecycleaware.codable.step2

import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Chronometer
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

/**
 * Create by AItsuki on 2019/2/26.
 */
class ChronometerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chronometer = Chronometer(this)
        chronometer.textSize = 30f
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        lp.gravity = Gravity.CENTER
        setContentView(chronometer, lp)

        val viewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        val startTime = viewModel.startTime ?: SystemClock.elapsedRealtime().also { viewModel.startTime = it }
        chronometer.base = startTime
        chronometer.start()
    }
}