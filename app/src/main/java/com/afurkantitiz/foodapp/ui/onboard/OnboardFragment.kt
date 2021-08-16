package com.afurkantitiz.foodapp.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afurkantitiz.foodapp.databinding.FragmentOnboardBinding
import com.afurkantitiz.foodapp.ui.onboard.animation.DepthTransformation

class OnboardFragment : Fragment() {
    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OnboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        val view = binding.root

        initViewPager()

        return view
    }

    private fun initViewPager() {
        adapter = OnboardAdapter(requireActivity())
        binding.onboardViewPager.setPageTransformer(DepthTransformation())
        binding.onboardViewPager.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}