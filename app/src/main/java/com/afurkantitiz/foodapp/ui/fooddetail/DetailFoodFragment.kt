package com.afurkantitiz.foodapp.ui.fooddetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.data.entity.cart.Cart
import com.afurkantitiz.foodapp.databinding.FragmentDetailMealBinding
import com.afurkantitiz.foodapp.ui.cart.CartViewModel
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFoodFragment : Fragment() {
    private var _binding: FragmentDetailMealBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFoodFragmentArgs by navArgs()
    private val viewModel: DetailFoodViewModel by viewModels()
    private lateinit var cartViewModel: CartViewModel

    private var adapter: DetailFoodAdapter = DetailFoodAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMealDetailForAPI()
        onClickListener()
        initViews()
    }

    private fun initViews() {
        binding.ingredientsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onClickListener() {
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getMealDetailForAPI() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, {
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

                    val meal = it.data!!.data
                    viewModel.meal = meal

                    cartViewModel =
                        ViewModelProviders.of(requireActivity()).get(CartViewModel::class.java)
                    val cart = Cart(
                        0,
                        meal.name,
                        args.restaurantName,
                        meal.image,
                        meal.price,
                        args.restaurantId,
                        meal.id
                    )

                    binding.addToCart.setOnClickListener {
                        cartViewModel.insertCart(cart)
                    }

                    Glide
                        .with(requireContext())
                        .load(meal.image)
                        .into(binding.imageView)

                    binding.mealNameTextView.text = meal.name
                    binding.mealDescription.text = meal.description
                    binding.mealPrice.text = meal.price + " $"

                    adapter.setIngredientList(meal.ingredients)
                    binding.ingredientsRecyclerView.adapter = adapter
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