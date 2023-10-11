package com.example.proyecttime.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.proyecttime.R
import com.example.proyecttime.databinding.ActivityMainBinding
import com.example.proyecttime.view.ViewViewModel.ClimateViewViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationClient: FusedLocationProviderClient
    private val climateViewViewModel : ClimateViewViewModel by viewModels()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val dateFormat = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        checkLocationPermission()
        getConnectApi()
    }

    private fun getConnectApi() {
        climateViewViewModel.climateModel.observe(this, Observer {
            val calendar: Calendar = Calendar.getInstance()
            val formattedDate = dateFormat.format(calendar.time)

            binding.tvDateTime.text = formattedDate

            binding.tvTemp.text = it.main.temp.toString()
            it.weather.forEach {
                binding.tvNamePronostic.text = it.description
//                Glide.with(this).load(it.icon).into(binding.imageView)
            }
            binding.tvHumidity.text = it.main.humidity.toString() + "%"
            binding.tvWindSpeed.text = it.wind.speed.toString() + "Km/h"
            loginAnimation(binding.imageView, R.raw.animation_lncfqr0t)
        })

    }

    private fun loginAnimation(imageView: LottieAnimationView, animation: Int) {
        val duration = 1000

        imageView.setAnimation(animation)
        imageView.playAnimation()
        imageView.speed = 0.8f
        imageView.repeatCount = LottieDrawable.INFINITE
    }

    private fun checkLocationPermission() {
        if (isLocationPermissionGranted()) {
            obtainLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        // Comprueba si se ha otorgado el permiso de ubicación
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        // Solicita el permiso de ubicación si aún no se ha otorgado
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtainLocation()
            } else {
                // Manejar el caso en el que el usuario no otorga el permiso
                // Puedes mostrar un mensaje de error o solicitar el permiso nuevamente.
            }
        }
    }

    private fun obtainLocation() {
        // Comprobar si se otorgaron los permisos de ubicación
        if (isLocationPermissionGranted()) {
            locationClient = LocationServices.getFusedLocationProviderClient(this)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitud = location.latitude
                    val longitud = location.longitude
                    climateViewViewModel.onCreate(latitud, longitud)
                    Log.i("latitud", "$latitud")
                } else {
                    // Manejar el caso en el que no se puede obtener la ubicación
                }
            }
        } else {
            // Manejar el caso en el que no se otorgaron los permisos de ubicación
        }
    }
}