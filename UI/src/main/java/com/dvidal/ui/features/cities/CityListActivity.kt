package com.dvidal.ui.features.cities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.dvidal.presentation.CityListViewModel
import com.dvidal.presentation.model.CityView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import com.dvidal.ui.R
import com.dvidal.ui.di.ViewModelFactory
import com.dvidal.ui.mapper.CityViewMapper
import com.dvidal.ui.utils.CitiesResponse
import com.google.gson.Gson
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_city_list.*
import timber.log.Timber
import java.io.BufferedReader
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class CityListActivity: AppCompatActivity() {

    private var adapter: CityListAdapter? = null
    @Inject lateinit var mapper: CityViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var cityListViewModel: CityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
        AndroidInjection.inject(this)

        cityListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CityListViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        cityListViewModel.getCityList().observe(this, Observer { resource ->
            resource?.let {
                handleDataState(it)
            }
        })
        cityListViewModel.fetchCityList()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBrowseRecycler() {

        adapter = CityListAdapter {city ->

            Intent().also {
                it.putExtra(PUT_EXTRA_CITY_ID, city.id)
                setResult(RESULT_CODE_CITY_ID, it)
                finish()
            }

        }
        recycler_cities.layoutManager = LinearLayoutManager(this)
        recycler_cities.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_cities.adapter = adapter
    }

    private fun loadCityList() {

        Thread {
        val inputStream = resources.openRawResource(R.raw.citylist)
        val allText = inputStream.bufferedReader().use(BufferedReader::readText)

            Gson().also {
                val citiesResponse = it.fromJson<CitiesResponse>(allText, CitiesResponse::class.java)
                cityListViewModel.saveCityList(citiesResponse.cities)
            }

        }.start()
    }

    private fun handleDataState(resource: Resource<List<CityView>>) {

        when (resource.status){
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_cities.visibility = View.GONE
            }
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                recycler_cities.visibility = View.VISIBLE
                resource.data?.let {
                    adapter?.cities = it.map { mapper.mapToView(it) }
                    adapter?.notifyDataSetChanged()
                }
            }
            ResourceState.EMTPTY -> loadCityList()
            ResourceState.ERROR -> {
                Timber.d("ERROR HERE!")
            }
        }
    }

    companion object {

        const val PUT_EXTRA_CITY_ID = "PUT_EXTRA_CITY_ID"

        const val RESULT_CODE_CITY_ID = 2000
        const val REQUEST_CODE_CITY_ID = 2001

        fun getStartIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }
}