package com.aitsuki.lifecycleaware.codable.step3

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

/**
 * Create by AItsuki on 2019/2/26.
 */
class LiveDataChronometerViewModel : ViewModel() {

    private var timer: Timer
    private val _liveData = MutableLiveData<Long>()
    val liveData: LiveData<Long>
        get() = _liveData

    init {
        val startTime = SystemClock.elapsedRealtime()
        timer = timer(period = 1000, action = {
            val newValue = (SystemClock.elapsedRealtime() - startTime) / 1000
            _liveData.postValue(newValue)
        })
    }

    override fun onCleared() {
        timer.cancel()
    }
}