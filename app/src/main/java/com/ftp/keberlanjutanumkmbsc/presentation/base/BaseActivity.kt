package com.ftp.keberlanjutanumkmbsc.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ftp.keberlanjutanumkmbsc.utils.showLoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var currentLoadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        initAppBar()
        handleIntentData()
        initRecyclerView()
        onCreateActivity()
        initObserver()
        initEditTextListener()
        initClickListener()
        initView()
        initData()
        initSearchView()
        initSpinner()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyActivity()
    }

    abstract fun setLayout(): View

    open fun initAppBar() {}

    open fun initSearchView() {}

    open fun onCreateActivity() {}

    open fun onDestroyActivity() {}

    open fun initView() {}

    open fun initData() {}

    open fun initRecyclerView() {}

    open fun handleIntentData() {}

    open fun initObserver() {}

    open fun initSpinner() {}

    open fun initBaseObserver(viewModel: BaseViewModel) {

        viewModel.showLoadingLiveData.observe(this) { show ->
            if (::currentLoadingDialog.isInitialized) {
                currentLoadingDialog.dismiss()
            }
            if (show) {
                if (this::currentLoadingDialog.isInitialized) {
                    currentLoadingDialog.dismiss()
                }
                currentLoadingDialog = showLoadingDialog()
            } else {
                if (this::currentLoadingDialog.isInitialized) {
                    currentLoadingDialog.dismiss()
                }
            }
        }

    }

    open fun initEditTextListener() {}

    open fun initClickListener() {}

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

}