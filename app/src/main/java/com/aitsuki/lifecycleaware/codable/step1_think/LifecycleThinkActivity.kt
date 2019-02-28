package com.aitsuki.lifecycleaware.codable.step1_think

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Create by AItsuki on 2019/2/26.
 */
class LifecycleThinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 延迟5秒注册观察者
        window.decorView.postDelayed({
            Log.d("lifecycle", "addObserver")
            lifecycle.addObserver(ThinkObserver())
        }, 5000)

        Log.d("lifecycle", "onInitialize")
    }
}

class ThinkObserver : LifecycleObserver {
    // ON_ANY表示接收所有生命周期事件
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChange(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("lifecycle", "observeEvent = ${event.name}, currentState = ${owner.lifecycle.currentState}")
    }
}