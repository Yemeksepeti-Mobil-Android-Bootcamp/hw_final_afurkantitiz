package com.afurkantitiz.foodapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.databinding.FragmentCartBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var cartAdapter: CartAdapter = CartAdapter()
    private lateinit var viewModel: CartViewModel
    private var foodIdList: ArrayList<String> = ArrayList()
    private lateinit var restaurantId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(requireActivity()).get(CartViewModel::class.java)
        viewModel.getAllCart()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        onClickListener()

        cartAdapter.setCarts(viewModel.allCarts)
        cartAdapter.notifyDataSetChanged()
    }

    private fun onClickListener() {
        for (cart in viewModel.allCarts){
            foodIdList.add(cart.foodId)
            restaurantId = cart.restaurantId
        }

        binding.confirmButton.setOnClickListener {
            viewModel.addOrderBulk(restaurantId, foodIdList).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.gone()
                        Toast.makeText(requireContext(), "Cart Confirm Success", Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.show()
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}