package com.dvidal.ui.handler

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.dvidal.ui.R
import com.dvidal.ui.features.currentweather.CurrentWeatherActivity
import java.lang.ref.WeakReference

/**
 * @author diegovidal on 03/10/2018.
 */
class RequestPermissionLocationHandler(val context: WeakReference<CurrentWeatherActivity>) {

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context.get()!!,
                        Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context.get()!!,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(context.get()!!)
                        .setTitle("Localization Permission")
                        .setMessage(R.string.permission_fine_location_details_message)
                        .setPositiveButton("Yes") { _, _ ->
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(context.get()!!,
                                    arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                                    MY_PERMISSIONS_REQUEST_LOCATION)
                        }
                        .create()
                        .show()


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context.get()!!,
                        arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }


    companion object {

        const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }
}