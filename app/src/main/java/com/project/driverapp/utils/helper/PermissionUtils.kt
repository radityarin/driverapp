package com.project.driverapp.utils.helper

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtils {

    const val REQUEST_LOCATION_PERMISSIONS = 1001
    const val REQUEST_CAMERA_PERMISSIONS = 1002
    const val REQUEST_CAMERA_AND_STORAGE_PERMISSIONS = 1003
    const val REQUEST_STORAGE_PERMISSIONS = 1004

    fun Activity.checkCameraPermission(permissionInvoke: () -> Unit?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        } else {
            permissionInvoke.invoke()
        }
    }

    fun Activity.checkCameraAndStoragePermission(
        permissionInvoke: () -> Unit?
    ) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraAndStoragePermission()
        } else {
            permissionInvoke.invoke()
        }
    }

    fun Activity.checkStoragePermission(
        permissionInvoke: () -> Unit?
    ) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestStoragePermission()
        } else {
            permissionInvoke.invoke()
        }
    }

    fun Activity.checkLocationPermission(
        permissionInvoke: () -> Unit?
    ) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            permissionInvoke.invoke()
        }
    }

    private fun Activity.requestLocationPermission() {
        val permissionList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_LOCATION_PERMISSIONS
        )
    }

    private fun Activity.requestStoragePermission() {
        val permissionList = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_STORAGE_PERMISSIONS
        )
    }

    private fun Activity.requestCameraPermission() {
        val permissionList = arrayOf(
            Manifest.permission.CAMERA
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_CAMERA_PERMISSIONS
        )
    }

    private fun Activity.requestCameraAndStoragePermission() {
        val permissionList = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_CAMERA_AND_STORAGE_PERMISSIONS
        )
    }
}
