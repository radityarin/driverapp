package com.project.driverapp.presentation.register.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.driverapp.databinding.DialogAcceptJobBinding
import com.project.driverapp.databinding.DialogRegisterResultBottomBinding

class AcceptJobDialog : BottomSheetDialogFragment {

    private var _binding: DialogAcceptJobBinding? = null
    private val binding get() = _binding!!

    var isSuccess = false
    var drawableId: Int = 0
    var title: String = ""
    var content: String = ""
    var button: String = ""

    constructor() : super()


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
        _binding = DialogAcceptJobBinding.inflate(inflater, container, false)
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
