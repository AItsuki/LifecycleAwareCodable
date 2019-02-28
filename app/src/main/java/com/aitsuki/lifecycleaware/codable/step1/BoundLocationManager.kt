package com.aitsuki.lifecycleaware.codable.step1

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

/**
 * Create by AItsuki on 2019/2/26.
 * https://stackoverflow.com/questions/43135948/memory-leak-when-removing-location-update-from-a-fragment-in-onpause
 */
class BoundLocationManager {

    companion object {
        fun bindLocationListenerIn(
            context: Context,
            lifecycleOwner: LifecycleOwner,
            callback: (Location?) -> Unit
        ) {
            val locationCallback = object : LocationCallback {
                override fun onLocationChanged(location: Location?) {
                    callback.invoke(location)
                }
            }
            BoundLocationListener(context.applicationContext, lifecycleOwner, WeakLocationListener(locationCallback))
        }
    }
}

interface LocationCallback {
    fun onLocationChanged(location: Location?)
}


private class BoundLocationListener(
    private val context: Context,
    lifecycleOwner: LifecycleOwner,
    private val listener: LocationListener
) : LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    private var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun addLocationListener() {
        // Note: Use the Fused Location Provider from Google Play Services instead.
        // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        locationManager?.run {
            requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, listener)
            Log.d("BoundLocationMgr", "Listener added")

            // Force an update with the last location, if available.
            val location = getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            listener.onLocationChanged(location)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun removeLocationListener() {
        locationManager?.run {
            removeUpdates(listener)
            Log.d("BoundLocationMgr", "Listener removed");
        }
        locationManager = null
    }
}

/**
 * LocationManager本身有内存泄漏问题，它无法正常的释放Listener
 */
private class WeakLocationListener(callback: LocationCallback) : LocationListener {

    private val callback = WeakReference<LocationCallback>(callback)

    override fun onLocationChanged(location: Location?) {
        callback.get()?.onLocationChanged(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}
