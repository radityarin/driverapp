package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.review.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftp.keberlanjutanumkmbsc.data.logic.Indikator
import com.ftp.keberlanjutanumkmbsc.databinding.DialogQuestionerResultBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QuestionerResultDialog : BottomSheetDialogFragment {

    private var _binding: DialogQuestionerResultBottomBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG = this.javaClass.simpleName
    }

    var listIndikator = mutableListOf<Indikator>()

    constructor() : super()

    constructor(listIndikator: MutableList<Indikator>) {
        this.listIndikator.addAll(listIndikator)
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
        _binding = DialogQuestionerResultBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
        }
    }

}
