package com.aitsuki.lifecycleaware.codable.step1

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aitsuki.lifecycleaware.codable.step1.BoundLocationManager.Companion.bindLocationListenerIn
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

/**
 * Create by AItsuki on 2019/2/26.
 */
class LocationActivity : AppCompatActivity() {

    companion object {
        const val RC_LOCATION = 111
    }

    private lateinit var textView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = TextView(this).also {
            it.text = "null"
        }
        setContentView(textView)
        bindLocationTask()
    }

    @AfterPermissionGranted(RC_LOCATION)
    private fun bindLocationTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            bindLocationListenerIn(this, this) {
                Log.d("LocationActivity", "location = $it")
                textView.text = it?.toString()
            }
        } else {
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, RC_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).build()
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}