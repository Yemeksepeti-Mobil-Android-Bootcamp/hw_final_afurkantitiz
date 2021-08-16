package com.afurkantitiz.foodapp.ui.onboard.onboardscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentFirstOnboardBinding

class FirstOnboardFragment : Fragment() {
    private var _binding: FragmentFirstOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstOnboardBinding.inflate(inflater, container, false)
        val view = binding.root

        viewPagerControlForButton()

        return view
    }

    private fun viewPagerControlForButton() {
        val onboardViewPager = activity?.findViewById<ViewPager2>(R.id.onboardViewPager)

        binding.firstOnboardNextButton.setOnClickListener {
            onboardViewPager?.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}