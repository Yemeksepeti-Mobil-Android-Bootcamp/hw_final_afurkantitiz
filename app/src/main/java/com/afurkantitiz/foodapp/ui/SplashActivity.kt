package com.afurkantitiz.foodapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afurkantitiz.foodapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*lottieAnimationListener()*/
    }

    /*    private fun lottieAnimationListener() {
            binding.splashLottieAnimation.addAnimatorListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    Log.v("Animation","Started")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (isOnboardShowed()){
                        findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                    }else {
                        findNavController().navigate(R.id.action_splashFragment_to_onboardFragment)
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
            val sharedPref = requireActivity().getSharedPreferences("onboard", Context.MODE_PRIVATE)
            return sharedPref.getBoolean("finished", false)
        }*/
}