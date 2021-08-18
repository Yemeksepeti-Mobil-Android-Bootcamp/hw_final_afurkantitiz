package com.afurkantitiz.foodapp.ui.onboard.onboardscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.local.SharedPrefManager
import com.afurkantitiz.foodapp.databinding.FragmentSecondOnboardBinding

class SecondOnboardFragment : Fragment() {
    private var _binding: FragmentSecondOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerControlForButton()
    }

    private fun viewPagerControlForButton() {
        binding.secondOnboardFinishButton.setOnClickListener {
            SharedPrefManager(requireContext()).setOnboardShow()
            findNavController().navigate(R.id.action_onboardFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}