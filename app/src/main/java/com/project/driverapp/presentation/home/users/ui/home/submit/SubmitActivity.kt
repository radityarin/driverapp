package com.project.driverapp.presentation.home.users.ui.home.submit

import android.content.pm.PackageManager
import android.view.View
import com.esafirm.imagepicker.features.ImagePickerLauncher
import com.esafirm.imagepicker.features.cameraonly.CameraOnlyConfig
import com.esafirm.imagepicker.features.registerImagePicker
import com.project.driverapp.databinding.ActivitySubmitBinding
import com.project.driverapp.presentation.base.BaseActivity
import com.project.driverapp.utils.helper.PermissionUtils
import com.project.driverapp.utils.helper.PermissionUtils.checkCameraAndStoragePermission
import com.project.driverapp.utils.setEditTextAndButtonListener

class SubmitActivity : BaseActivity() {

    private var _binding: ActivitySubmitBinding? = null
    private val binding get() = _binding!!
    private lateinit var imagePickerLauncher: ImagePickerLauncher

    override fun setLayout(): View {
        _binding = ActivitySubmitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        setupImagePickerLauncher()
    }

    override fun initClickListener() {
        with(binding) {
            btnAddImage.setOnClickListener {
                launchImagePicker()
            }
            tvEnabled.setOnClickListener {
                finish()
            }
            tvBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupImagePickerLauncher() {
        imagePickerLauncher = registerImagePicker { img ->
            img.map {
                binding.btnAddImage.setImageURI(it.uri)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionUtils.REQUEST_CAMERA_AND_STORAGE_PERMISSIONS -> {
                when {
                    grantResults.isEmpty() -> {
                    }
                    grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED -> {
                        launch()
                    }
                    else -> {
                    }
                }
            }
        }
    }


    private fun launchImagePicker() {
        checkCameraAndStoragePermission {
            launch()
        }
    }

    private fun launch() {
        imagePickerLauncher.launch(CameraOnlyConfig())
    }


    override fun initEditTextListener() {
        with(binding) {
            setEditTextAndButtonListener(
                listOf(
                    etDeskripsi,
                    etPlatNomor,
                ), tvEnabled, tvDisabled
            )
        }
    }

}