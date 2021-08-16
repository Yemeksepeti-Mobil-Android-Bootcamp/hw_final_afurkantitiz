package com.afurkantitiz.foodapp.ui.onboard.onboardscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afurkantitiz.foodapp.databinding.FragmentErrorOnboardBinding

class ErrorOnboardFragment : Fragment() {
    private var _binding: FragmentErrorOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorOnboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}