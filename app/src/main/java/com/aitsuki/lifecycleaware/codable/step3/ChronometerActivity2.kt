package com.aitsuki.lifecycleaware.codable.step3

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

/**
 * Create by AItsuki on 2019/2/26.
 */
class ChronometerActivity2 : AppCompatActivity() {

    private val observer = Observer<Long> {
        // do Something
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.textSize = 30f
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        lp.gravity = Gravity.CENTER
        setContentView(textView, lp)

        val viewModel = ViewModelProviders.of(this).get(LiveDataChronometerViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            Log.d("Chronometer2", "$it")
            textView.text = "$it"
        })

        viewModel.liveData.observeForever(observer)

        viewModel.liveData.removeObserver(observer)
    }
}