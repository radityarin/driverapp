package com.ftp.keberlanjutanumkmbsc.presentation.register.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.databinding.DialogResultBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultDialog : BottomSheetDialogFragment {

    private var _binding: DialogResultBottomBinding? = null
    private val binding get() = _binding!!

    var isSuccess = false
    var drawableId: Int = 0
    var title: String = ""
    var content: String = ""
    var button: String = ""

    constructor() : super()

    constructor(isSuccess: Boolean) {
        this.isSuccess = isSuccess
        drawableId = if (isSuccess) {
            R.drawable.ic_popup_success
        } else {
            R.drawable.ic_popup_failed
        }
        title = if (isSuccess) {
            "Berhasil"
        } else {
            "Gagal"
        }
        content = if (isSuccess) {
            "Selamat! Anda berhasil membuat akun.\n" +
                    "Ayo, jelajahi StrategiKu!"
        } else {
            "Oops.. Anda gagal membuat akun.Silahkan\nperiksa kembali jaringan koneksi Anda."
        }
        button = if (isSuccess) {
            "Ok"
        } else {
            "Ok"
        }
    }

    companion object {
        val TAG = this.javaClass.simpleName
    }

    private var listener: OkListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OkListener
        } catch (castException: ClassCastException) {

        }
    }

    interface OkListener {
        fun onOkClickListener(isSuccess: Boolean)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogResultBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            tvClose.setOnClickListener {
                listener?.onOkClickListener(isSuccess)
                dismiss()
            }
            tvOk.setOnClickListener {
                listener?.onOkClickListener(isSuccess)
                dismiss()
            }
        }
    }

}
