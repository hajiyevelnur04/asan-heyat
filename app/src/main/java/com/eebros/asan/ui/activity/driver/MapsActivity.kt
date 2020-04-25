package com.eebros.asan.ui.activity.driver

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.Constants.Companion.LOCATION_PERMISSION_REQUEST_CODE
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.common.RiderDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.asan_toolbar_layout.*
import javax.inject.Inject


class MapsActivity : BaseActivity(), OnMapReadyCallback {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MapsViewModel

    lateinit var searchContainer: LinearLayout

    lateinit var btn_homeRiderMainHandicap: ImageButton
    lateinit var btn_ban_mainChildSeat: ImageButton

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        viewModel = ViewModelProvider(this, factory)[MapsViewModel::class.java]

        searchContainer = findViewById(R.id.searchContainer)
        btn_homeRiderMainHandicap = findViewById(R.id.btn_homeRiderMainHandicap)
        btn_ban_mainChildSeat = findViewById(R.id.ban_mainChildSeat)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        toolbar_back_button.setOnClickListener {
            onBackPressed()
        }

        toolbar_title.text = getString(R.string.taxi)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        searchContainer.setOnClickListener{
            startActivity(Intent(this, SearchRiderActivity::class.java))
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }

        btn_homeRiderMainHandicap.setOnClickListener{
            val title = "handicap accessibility"
            val msg = "Do you want ti get handicap seat in your ride"

            RiderDialog(this).dialogCreate(title,msg,btn_homeRiderMainHandicap)

        }

        btn_ban_mainChildSeat.setOnClickListener{
            startActivity(Intent(this, SelectDriver::class.java))
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        // zoom buttons control
        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.uiSettings.isCompassEnabled = false
        mMap.uiSettings.isMyLocationButtonEnabled = false

        mMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener {
            return@OnMarkerClickListener false
        })

        setUpMap()
    }

    private fun placeMarkerOnMap(location: LatLng) {

        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(
            markerOptions.icon(
                BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(resources, R.drawable.ic_pin_current_location)
                )
            )
        )
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

        btn_homeRiderMainFocus.setOnClickListener{
            val latLng = LatLng(lastLocation.latitude, lastLocation.longitude)
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
            mMap.animateCamera(cameraUpdate)
        }
    }

}
