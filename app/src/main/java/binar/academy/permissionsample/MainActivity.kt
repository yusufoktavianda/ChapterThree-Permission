package binar.academy.permissionsample

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    private lateinit var locationButton: Button
    private lateinit var imageButton: Button
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageButton = findViewById(R.id.load_image_button)
        locationButton = findViewById(R.id.requet_permission_button)

        imageButton.setOnClickListener {
            loadImage()
        }
        locationButton.setOnClickListener {
            requestPermissionLocation()
        }
    }

    private fun loadImage() {
        imageView = findViewById(R.id.imageView)
        Glide.with(this)
            .load("https://img.icons8.com/plasticine/2x/flower.png")
            .circleCrop()
            .into(imageView)
    }

    private fun requestPermissionLocation() {
        val permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Location DIIZINKAN", Toast.LENGTH_LONG).show()
                getLongLat()
            }else{
                requestLocationPermission()
            }
    }
    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 201)
    }

    @SuppressLint("MissingPermission")
    private fun getLongLat() {
        val locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        Toast.makeText(
            this,
            "Lat ${location?.latitude} Long: ${location?.longitude}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            201 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, "Permissions for Location Permitted", Toast.LENGTH_LONG)
                        .show()
                    getLongLat()
                } else {
                    Toast.makeText(this, "Permissions for Location Denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> {
                Toast.makeText(this, "The request code doesn't match", Toast.LENGTH_LONG).show()
            }
        }
    }
}

/**
 * 1. buatlah function dengan nama loadimage
 * 2. pindahin code glide di function loadimage
 * 3. buatlah function yang namanya requestPermissionLocation
 * 4. di dalam requestPermissionLocation tambahkan code untuk request akses location
 * yang ada di dalam slide
 */