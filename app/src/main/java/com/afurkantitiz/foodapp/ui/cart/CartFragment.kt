package com.afurkantitiz.foodapp.ui.cart

import android.annotation.SuppressLint
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
import com.afurkantitiz.foodapp.utils.hide
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

        initViews()
        onClickListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        cartAdapter.setCarts(viewModel.allCarts)
        cartAdapter.notifyDataSetChanged()

        if (viewModel.allCarts.size == 0){
            binding.confirmButton.hide()
            binding.clearButton.hide()
        }else {
            binding.confirmButton.show()
            binding.clearButton.show()
        }
    }

    private fun onClickListener() {
        for (cart in viewModel.allCarts) {
            foodIdList.add(cart.foodId)
            restaurantId = cart.restaurantId
        }

        binding.confirmButton.setOnClickListener {
            postOrderBulk()
        }

        binding.clearButton.setOnClickListener {
            viewModel.deleteCart(viewModel.allCarts)
            findNavController().navigate(R.id.action_cartFragment_self)
        }
    }

    private fun postOrderBulk() {
        viewModel.addOrderBulk(restaurantId, foodIdList).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.lottieLoading.show()
                    binding.lottieLoading.playAnimation()
                    binding.screenLayout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.lottieLoading.cancelAnimation()
                    binding.lottieLoading.gone()
                    binding.screenLayout.show()

                    viewModel.deleteCart(viewModel.allCarts)
                    findNavController().navigate(R.id.action_cartFragment_self)
                }
                Resource.Status.ERROR -> {
                    binding.lottieLoading.gone()
                    Toast.makeText(
                        requireContext(),
                        "Network Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}