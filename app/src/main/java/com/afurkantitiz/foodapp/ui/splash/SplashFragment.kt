package com.afurkantitiz.foodapp.ui.splash

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.local.SharedPrefManager
import com.afurkantitiz.foodapp.databinding.FragmentSplashBinding
import com.afurkantitiz.foodapp.ui.MainActivity
import com.auth0.android.jwt.JWT

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        initLottieAnimation()

        return binding.root
    }

    private fun initLottieAnimation() {
        binding.splashLottieAnimation.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                Log.v("Animation","Started")
            }

            override fun onAnimationEnd(animation: Animator?) {
                val token = getToken()

                if (!isOnboardShowed()){
                    findNavController().navigate(R.id.action_splashFragment_to_onboardFragment)
                }else {
                    if (token.isNullOrEmpty()){
                        findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                    }else {
                        val jwt = JWT(token)

                        if (jwt.isExpired(0)){
                            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                        }else {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.v("Animation","Canceled")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.v("Animation","Repeated")
            }
        })
    }

    private fun isOnboardShowed(): Boolean {
        return SharedPrefManager(requireContext()).isOnboardShow()
    }

    private fun getToken(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}