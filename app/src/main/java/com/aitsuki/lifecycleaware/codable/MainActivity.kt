package com.aitsuki.lifecycleaware.codable

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aitsuki.lifecycleaware.codable.step1.LocationActivity
import com.aitsuki.lifecycleaware.codable.step1_think.LifecycleThinkActivity
import com.aitsuki.lifecycleaware.codable.step2.ChronometerActivity
import com.aitsuki.lifecycleaware.codable.step3.ChronometerActivity2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.bt_step1).setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

        findViewById<View>(R.id.bt_step1_think).setOnClickListener {
            startActivity(Intent(this, LifecycleThinkActivity::class.java))
        }

        findViewById<View>(R.id.bt_step2).setOnClickListener {
            startActivity(Intent(this, ChronometerActivity::class.java))
        }

        findViewById<View>(R.id.bt_step3).setOnClickListener {
            startActivity(Intent(this, ChronometerActivity2::class.java))
        }
    }
}
