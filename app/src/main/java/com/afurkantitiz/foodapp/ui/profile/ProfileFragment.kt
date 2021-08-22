package com.afurkantitiz.foodapp.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentProfileBinding
import com.afurkantitiz.foodapp.ui.SplashActivity
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
    private var adapter = OrderAdapter()

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

        getProfileForAPI()
        onClickListener()
        getOrderForAPI()
    }

    private fun onClickListener() {
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.editButton.setOnClickListener {
            val profileUpdateFragment = ProfileUpdateFragment()
            profileUpdateFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_Demo_BottomSheetDialog
            )
            profileUpdateFragment.show(
                requireActivity().supportFragmentManager,
                "BottomSheetDialog"
            )
        }

        binding.logOut.setOnClickListener {
            viewModel.logOut()
            val intent = Intent(context, SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun getProfileForAPI() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.lottieLoading.show()
                    binding.lottieLoading.playAnimation()
                    binding.screenLayout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.lottieLoading.cancelAnimation()
                    binding.lottieLoading.gone()
                    binding.screenLayout.show()

                    val userData = it.data?.user
                    binding.emailAddress.text = userData?.email
                    binding.place.text = userData?.address
                    binding.profileName.text = userData?.name
                    binding.telephoneNumber.text = userData?.phone

                    Glide.with(requireContext())
                        .load(userData?.profileImage)
                        .into(binding.profileImageView)
                }
                Resource.Status.ERROR -> {
                    binding.lottieLoading.gone()
                    Toast.makeText(
                        requireContext(),
                        "Network Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun getOrderForAPI() {
        viewModel.getOrders().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.lottieOrderLoading.show()
                    binding.lottieOrderLoading.playAnimation()
                    binding.ordersRecyclerView.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.lottieOrderLoading.cancelAnimation()
                    binding.lottieOrderLoading.gone()
                    binding.ordersRecyclerView.show()

                    response.data?.orderList?.let {
                        binding.ordersRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.ordersRecyclerView.adapter = adapter
                        adapter.setData(it)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.lottieOrderLoading.gone()
                    Toast.makeText(
                        requireContext(),
                        "Network Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}