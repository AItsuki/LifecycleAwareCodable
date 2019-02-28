package com.aitsuki.lifecycleaware.codable.step2

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Create by AItsuki on 2019/2/26.
 */
class ChronometerViewModel : ViewModel() {

    var startTime: Long? = null

    // onCleared方法是在Activity真正结束时才调用
    override fun onCleared() {
        super.onCleared()
        Log.d("ChronometerVM", "onCleared, startTime = $startTime")
    }
}