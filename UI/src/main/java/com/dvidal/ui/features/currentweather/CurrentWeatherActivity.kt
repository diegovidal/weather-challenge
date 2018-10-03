package com.dvidal.ui.features.currentweather

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dvidal.presentation.CurrentWeatherViewModel
import com.dvidal.presentation.model.CurrentWeatherView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import com.dvidal.ui.R
import com.dvidal.ui.di.ViewModelFactory
import com.dvidal.ui.mapper.CurrentWeatherViewMapper

import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.ResultReceiver
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.dvidal.ui.features.cities.CityListActivity
import com.dvidal.ui.features.cities.CityListActivity.Companion.PUT_EXTRA_CITY_ID
import com.dvidal.ui.features.cities.CityListActivity.Companion.REQUEST_CODE_CITY_ID
import com.dvidal.ui.features.cities.CityListActivity.Companion.RESULT_CODE_CITY_ID
import com.dvidal.ui.handler.RequestPermissionLocationHandler
import com.dvidal.ui.service.MyLocationService.Companion.PUT_LATITUDE
import com.dvidal.ui.service.MyLocationService.Companion.PUT_LONGITUDE
import com.dvidal.ui.service.MyLocationService.Companion.PUT_RECEIVER
import com.dvidal.ui.service.MyLocationService.Companion.RESULT_CODE_RECEIVER
import kotlinx.android.synthetic.main.activity_current_weather.*
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author diegovidal on 02/10/2018.
 */
class CurrentWeatherActivity: AppCompatActivity(){

    @Inject lateinit var mapper: CurrentWeatherViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    private var requestPermissionLocationHandler: RequestPermissionLocationHandler? = null

    private var resultReceiverListener: ResultReceiverListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather)
        AndroidInjection.inject(this)

        currentWeatherViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CurrentWeatherViewModel::class.java)

        requestPermissionLocationHandler = RequestPermissionLocationHandler(
                WeakReference(this@CurrentWeatherActivity))

        // London default
        currentWeatherViewModel.fetchCurrentWeatherWithCity(3489741)
    }

    override fun onStart() {
        super.onStart()
        currentWeatherViewModel.getCurrentWeather().observe(this,
                Observer<Resource<CurrentWeatherView>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CITY_ID && resultCode == RESULT_CODE_CITY_ID){

            val cityId = data?.getLongExtra(PUT_EXTRA_CITY_ID, -1) ?: -1
            currentWeatherViewModel.clearCurrentWeatherWithCity(cityId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.my_location, menu)
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_my_location -> {

                if (requestPermissionLocationHandler?.checkLocationPermission() == true)
                    startLocationService()
                true
            }
            R.id.action_search -> {
                startActivityForResult(CityListActivity.getStartIntent(this), REQUEST_CODE_CITY_ID)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requestPermissionLocationHandler = null
        resultReceiverListener = null
    }

    private fun startLocationService() {

        resultReceiverListener = null
        resultReceiverListener = ResultReceiverListener(null)

        Intent("MY_LOCATION_SERVICE").also {
            it.setPackage(packageName)
            it.putExtra(PUT_RECEIVER, resultReceiverListener)
            startService(it)
        }
    }

    private fun stopLocationService() {

        Intent("MY_LOCATION_SERVICE").also {
            it.setPackage(packageName)
            stopService(it)
        }
    }


    private fun handleDataState(resource: Resource<CurrentWeatherView>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                Timber.d("AEEEEEEEE")
                updateOutputUi(resource.data)
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
            }
            ResourceState.ERROR -> {
                Timber.e("ERROR MESSAGE: ${resource.message}")
            }
            else -> Timber.d("")
        }
    }

    private fun updateOutputUi(currentWeather: CurrentWeatherView?) {
        progress.visibility = View.GONE

        currentWeather?.let {
            title = currentWeather.name

            tv_temperature.text = getString(R.string.tv_temperature_format, it.temp.toInt())
            tv_wind_speed.text = getString(R.string.tv_wind_speed_format,it.windSpeed)
            tv_humidity.text = getString(R.string.tv_humidity_format,it.humidity)
            tv_pressure.text = getString(R.string.tv_pressure_format,it.pressure)

            tv_last_update.text = getString(R.string.tv_last_update_format, getTime())
        }
    }

    private fun getTime(): String {

        val df = SimpleDateFormat("HH:mm", Locale.getDefault())
        return df.format(Date())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RequestPermissionLocationHandler.MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        startLocationService()
                    }
                }
                return
            }
        }
    }

    inner class ResultReceiverListener(handler: Handler?): ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)

            if (resultCode == RESULT_CODE_RECEIVER){
                val latitude = resultData?.getDouble(PUT_LATITUDE) ?: 0.0
                val longitude = resultData?.getDouble(PUT_LONGITUDE) ?: 0.0

                runOnUiThread {
                    currentWeatherViewModel.clearCurrentWeatherWithPosition(latitude, longitude)
                }

            }
        }
    }
}