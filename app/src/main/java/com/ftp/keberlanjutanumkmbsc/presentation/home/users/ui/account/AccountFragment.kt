package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.databinding.FragmentAccountBinding
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.utils.goToLanding
import com.ftp.keberlanjutanumkmbsc.utils.hideView
import com.ftp.keberlanjutanumkmbsc.utils.showCustomToast
import com.ftp.keberlanjutanumkmbsc.utils.showView
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val accountViewModel: AccountViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvEdit.setOnClickListener {
                enableEditText(true)
                tvSimpan.showView()
            }
            tvKeluar.setOnClickListener {
                requireActivity().goToLanding()
            }
            tvSimpan.setOnClickListener {
                accountViewModel.editData(
                    User(
                        idUser = ProfilePrefs.idFirebase,
                        email = ProfilePrefs.email,
                        fullname = etNamaLengkap.text.toString(),
                        jenisKelamin = spnJenisKelamin.selectedItem.toString(),
                        pekerjaan = etPekerjaan.text.toString(),
                        namaUsaha = etNamaUsaha.text.toString(),
                        alamat = etAlamat.text.toString(),
                        noHP = ProfilePrefs.noHP,
                        tahunPengalaman = etPengalamanUsaha.text.toString(),
                        jenisProdukYangDijual = etJenisProduk.text.toString(),
                    )
                )
                showCustomToast(message = "Data kamu berhasil diubah", isAboveFAB = true)
                enableEditText(false)
                tvSimpan.hideView()
            }
        }
        initView()
    }

    private fun initView() {
        with(binding) {
            enableEditText(false)
            etNamaLengkap.setText(ProfilePrefs.fullname)
            setSpinner(spnJenisKelamin, ProfilePrefs.jenisKelamin)
            etPekerjaan.setText(ProfilePrefs.pekerjaan)
            etNamaUsaha.setText(ProfilePrefs.namaUsaha)
            etAlamat.setText(ProfilePrefs.alamat)
            etPengalamanUsaha.setText(ProfilePrefs.tahunPengalaman)
            etJenisProduk.setText(ProfilePrefs.jenisProdukYangDijual)
            tvSimpan.hideView()
        }
    }

    private fun setSpinner(spnJenisKelamin: Spinner, jenisKelamin: String) {
        when (jenisKelamin) {
            "Laki-laki" -> {
                spnJenisKelamin.setSelection(1)
            }
            else -> {
                spnJenisKelamin.setSelection(2)
            }
        }
    }

    private fun enableEditText(enabled: Boolean) {
        with(binding) {
            etNamaLengkap.isEnabled = enabled
            spnJenisKelamin.isEnabled = enabled
            etPekerjaan.isEnabled = enabled
            etNamaUsaha.isEnabled = enabled
            etAlamat.isEnabled = enabled
            etPengalamanUsaha.isEnabled = enabled
            etJenisProduk.isEnabled = enabled
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}