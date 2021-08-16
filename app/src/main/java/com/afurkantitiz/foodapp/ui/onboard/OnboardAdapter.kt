package com.afurkantitiz.foodapp.ui.onboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.afurkantitiz.foodapp.ui.onboard.onboardscreen.ErrorOnboardFragment
import com.afurkantitiz.foodapp.ui.onboard.onboardscreen.FirstOnboardFragment
import com.afurkantitiz.foodapp.ui.onboard.onboardscreen.SecondOnboardFragment

private const val FRAGMENT_COUNT = 2

class OnboardAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnboardFragment()
            1 -> SecondOnboardFragment()
            else -> ErrorOnboardFragment()
        }
    }
}