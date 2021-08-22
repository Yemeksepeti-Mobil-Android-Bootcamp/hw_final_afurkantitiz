package com.afurkantitiz.foodapp.ui.authentication.signup

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentSignUpBinding
import com.afurkantitiz.foodapp.ui.MainActivity
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListener()
    }

    private fun onClickListener() {
        binding.signUpGoToSignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.sigUpCreateAccButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val name = binding.signUpNameEditText.text.toString()
        val email = binding.signUpEmailEditText.text.toString()
        val password = binding.signUpPasswordEditText.text.toString()

        viewModel.register(name, email, password).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.lottieLoading.show()
                    binding.lottieLoading.playAnimation()
                    binding.screenLayout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.lottieLoading.cancelAnimation()
                    binding.lottieLoading.gone()
                    binding.screenLayout.gone()

                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                Resource.Status.ERROR -> {
                    binding.lottieLoading.gone()

                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Network Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                    dialog.show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}