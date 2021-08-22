package com.afurkantitiz.foodapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentRestaurantAddBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantAddFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRestaurantAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeCuisine()
        initializePayment()
        setOnclickListener()
    }

    private fun setOnclickListener() {
        binding.addRestaurantButton.setOnClickListener {
            addRestaurant()
        }
    }

    private fun addRestaurant() {
        val name = binding.restaurantNameEditText.editText?.text.toString()
        val cuisine = binding.cuisineSpinner.selectedItem.toString()
        val deliveryInfo = binding.restaurantDeliveryInfoEditText.editText?.text.toString()
        val deliveryTime = binding.restaurantDeliveryTimeEditText.text.toString()
        val address = binding.restaurantAddressEditText.editText?.text.toString()
        val minDeliveryFee = binding.restaurantDeliveryFeeEditText.text.toString()
        val paymentMethods = binding.multiSelectSpinner.selectedItem.toString()

        viewModel.addRestaurant(name, cuisine, deliveryInfo, deliveryTime, address, "Altınkaya",
        minDeliveryFee, paymentMethods, 7.0, "https://www.qsrmagazine.com/sites/default/files/styles/story_page/public/popeyes.jpg?itok=mSr5gG37").observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    this.dismiss()
                    findNavController().navigate(R.id.action_restaurantsFragment_self)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initializeCuisine() {
        val cities = resources.getStringArray(R.array.Cuisines)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        binding.cuisineSpinner.adapter = adapter
    }

    private fun initializePayment() {
        val paymentMethods = resources.getStringArray(R.array.RestaurantPaymentMethods)
        val adapter = ArrayAdapter(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_dropdown_item,
            paymentMethods
        )
        binding.multiSelectSpinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}