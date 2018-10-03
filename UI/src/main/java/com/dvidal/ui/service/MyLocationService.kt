package com.dvidal.ui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.util.Log
import android.widget.Toast

/**
 * @author diegovidal on 03/10/2018.
 */
class MyLocationService : Service() {

    private var mLocationManager: LocationManager? = null
    private var mLocationListeners = arrayOf(LocationListener(LocationManager.GPS_PROVIDER),
            LocationListener(LocationManager.NETWORK_PROVIDER))

    var resultReceiver: ResultReceiver? = null

    inner class LocationListener internal constructor(provider: String) : android.location.LocationListener {

        private var mLastLocation: Location

        init {
            Log.e(TAG, "LocationListener $provider")
            mLastLocation = Location(provider)
        }

        override fun onLocationChanged(location: Location) {
            Log.e(TAG, "onLocationChanged: $location")
            mLastLocation.set(location)

            Bundle().also {
                it.putDouble(PUT_LATITUDE, mLastLocation.latitude)
                it.putDouble(PUT_LONGITUDE, mLastLocation.longitude)
                resultReceiver?.send(RESULT_CODE_RECEIVER, it)
            }
            removeLocationUpdates()
        }

        override fun onProviderDisabled(provider: String) {
            Log.e(TAG, "onProviderDisabled: $provider")
        }

        override fun onProviderEnabled(provider: String) {
            Log.e(TAG, "onProviderEnabled: $provider")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            Log.e(TAG, "onStatusChanged: $provider")
        }
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.e(TAG, "onStartCommand")
        resultReceiver = intent.getParcelableExtra(PUT_RECEIVER)

        if (mLocationManager == null) {
            initializeLocationManager()
            try {
                mLocationManager?.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL.toLong(), LOCATION_DISTANCE,
                        mLocationListeners[1])
            } catch (ex: java.lang.SecurityException) {
                Log.i(TAG, "fail to request location update, ignore", ex)
            } catch (ex: IllegalArgumentException) {
                Log.d(TAG, "network provider does not exist, " + ex.message)
            }

            try {
                mLocationManager?.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, LOCATION_INTERVAL.toLong(), LOCATION_DISTANCE,
                        mLocationListeners[0])

            } catch (ex: java.lang.SecurityException) {
                Log.i(TAG, "fail to request location update, ignore", ex)
            } catch (ex: IllegalArgumentException) {
                Log.d(TAG, "gps provider does not exist " + ex.message)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        super.onDestroy()
        removeLocationUpdates()

    }

    private fun removeLocationUpdates() {
        if (mLocationManager != null) {
            for (locationListener in mLocationListeners) {
                try {
                    mLocationManager?.removeUpdates(locationListener)
                    mLocationManager = null
                    resultReceiver = null
                } catch (ex: Exception) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex)
                }
            }
        }
    }

    private fun initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager")
        mLocationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    companion object {
        private const val TAG = "MY_LOCATION_SERVICE"
        private const val LOCATION_INTERVAL = 1000
        private const val LOCATION_DISTANCE = 5f

        const val PUT_LATITUDE = "PUT_LATITUDE"
        const val PUT_RECEIVER = "PUT_RECEIVER"
        const val PUT_LONGITUDE = "PUT_LONGITUDE"

        const val RESULT_CODE_RECEIVER = 1001
    }
}