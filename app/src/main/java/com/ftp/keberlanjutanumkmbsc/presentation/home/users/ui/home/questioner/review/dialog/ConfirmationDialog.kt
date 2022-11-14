package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.review.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftp.keberlanjutanumkmbsc.databinding.DialogConfirmationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmationDialog : BottomSheetDialogFragment() {

    private var _binding: DialogConfirmationBottomBinding? = null
    private val binding get() = _binding!!

    var isSuccess = false
    var drawableId: Int = 0
    var title: String = ""
    var content: String = ""
    var button: String = ""

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
        fun onOkClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogConfirmationBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            tvClose.setOnClickListener {
                dismiss()
            }
            tvOk.setOnClickListener {
                listener?.onOkClickListener()
                dismiss()
            }
            tvCancel.setOnClickListener {
                dismiss()
            }
        }
    }

}
