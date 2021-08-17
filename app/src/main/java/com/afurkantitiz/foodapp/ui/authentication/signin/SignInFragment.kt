package com.afurkantitiz.foodapp.ui.authentication.signin

import android.animation.Animator
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.local.SharedPrefManager
import com.afurkantitiz.foodapp.databinding.FragmentSignInBinding
import com.afurkantitiz.foodapp.ui.MainActivity
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick() {
        binding.signInGoToSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInLoginButton.setOnClickListener {
            loginFunc()
        }
    }

    private fun loginFunc() {

        val email = binding.signInEmailEditText.text.toString()
        val password = binding.signInPasswordEditText.text.toString()

        viewModel.signIn(email, password).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.signInProgressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.signInProgressBar.gone()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                Resource.Status.ERROR -> {
                    binding.signInProgressBar.gone()
                    val dialog = AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("${it.message}")
                        .setPositiveButton("ok") { dialog, button ->
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

    private fun getToken(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }
}