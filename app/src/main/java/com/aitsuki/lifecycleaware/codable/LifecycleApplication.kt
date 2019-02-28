package com.aitsuki.lifecycleaware.codable

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Create by AItsuki on 2019/2/26.
 */
class LifecycleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        // Normal app init code...
    }
}