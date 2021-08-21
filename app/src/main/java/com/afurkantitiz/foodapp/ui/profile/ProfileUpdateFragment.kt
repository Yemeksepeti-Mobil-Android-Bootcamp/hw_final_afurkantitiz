package com.afurkantitiz.foodapp.ui.profile

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.profile.UserRequest
import com.afurkantitiz.foodapp.databinding.FragmentProfileUpdateBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileUpdateFragment() : BottomSheetDialogFragment() {
    private var _binding: FragmentProfileUpdateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserInfo()
        onClickListener()
    }

    private fun getUserInfo() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                    binding.layout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    binding.layout.show()

                    val userData = it.data?.user

                    binding.emailEditText.text = userData?.email?.toEditable()
                    binding.nameEditText.text = userData?.name?.toEditable()
                    binding.phoneEditText.text = userData?.phone?.toEditable()
                    binding.addressEditText.text = userData?.address?.toEditable()
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.show()
                }
            }
        })
    }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun onClickListener() {
        binding.updateButton.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val name = binding.nameEditText.text.toString()
        val mail = binding.emailEditText.text.toString()
        val phone = binding.phoneEditText.text.toString()
        val address = binding.addressEditText.text.toString()

        val user = UserRequest(mail, name, address, phone)

        viewModel.updateUser(user).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                    binding.layout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    binding.layout.show()
                    this.dismiss()
                    findNavController().navigate(R.id.action_profileFragment_self)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}