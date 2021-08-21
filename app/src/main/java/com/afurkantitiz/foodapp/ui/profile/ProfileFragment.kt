package com.afurkantitiz.foodapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentProfileBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfileData()
        onClickListener()
    }

    private fun onClickListener() {
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.editButton.setOnClickListener {
            val profileUpdateFragment = ProfileUpdateFragment()
            profileUpdateFragment.setStyle(
                    DialogFragment.STYLE_NORMAL,
                    R.style.ThemeOverlay_Demo_BottomSheetDialog)
                profileUpdateFragment.show(requireActivity().supportFragmentManager, "BottomSheetDialog")
        }
    }

    private fun getProfileData() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                    binding.profileLayout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    binding.profileLayout.show()

                    val userData = it.data?.user
                    binding.emailAddress.text = userData?.email
                    binding.place.text = userData?.address
                    binding.profileName.text = userData?.name
                    binding.telephoneNumber.text = userData?.phone

                    Glide.with(binding.profileImageView.context)
                        .load(userData?.profileImage)
                        .into(binding.profileImageView)
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