package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.afurkantitiz.foodapp.data.entity.food.Meal
import com.afurkantitiz.foodapp.databinding.FragmentDetailRestaurantBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.DialogFragment
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.utils.hide

@AndroidEntryPoint
class DetailRestaurantFoodsFragment : Fragment() {
    private var _binding: FragmentDetailRestaurantBinding? = null
    private val binding get() = _binding!!

    private val foodsViewModel: DetailRestaurantFoodsViewModel by viewModels()
    private val args: DetailRestaurantFoodsFragmentArgs by navArgs()

    private lateinit var currentRole: String
    private var adapter: DetailRestaurantFoodsAdapter = DetailRestaurantFoodsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserForAPI()
        initViews()
        getRestaurantDetailForAPI()
        onClickListener()
    }

    private fun initViews() {
        binding.mealsRecyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun onClickListener() {
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.addFoodButton.setOnClickListener {
            val foodAddFragment = FoodAddFragment(restaurantId = args.restaurantId)
            foodAddFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_Demo_BottomSheetDialog
            )
            foodAddFragment.show(
                requireActivity().supportFragmentManager,
                "RestaurantAddBottomSheet"
            )
        }
    }

    private fun getRestaurantDetailForAPI() {
        foodsViewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner, {
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

                    val restaurant = it.data!!.data
                    setMealsAdapter(restaurant.meals, restaurant.id, restaurant.name)

                    Glide.with(binding.restaurantImageView.context)
                        .load(restaurant.image)
                        .into(binding.restaurantImageView)

                    binding.restaurantNameTextView.text = restaurant.name
                    binding.deliveryFee.text = restaurant.minimumDeliveryFee
                    binding.deliveryInfo.text = restaurant.deliveryInfo
                    binding.payments.text = restaurant.paymentMethods
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

    private fun getUserForAPI() {
        foodsViewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.v("getUser", "Loading")
                }
                Resource.Status.SUCCESS -> {
                    val userData = it.data!!.user
                    currentRole = userData!!.role

                    isAdmin(currentRole)
                }
                Resource.Status.ERROR -> {
                    Log.v("getUser", "Error")
                }
            }
        })
    }

    private fun isAdmin(currentRole: String) {
        if(currentRole == "admin"){
            binding.addFoodButton.show()
        }else {
            binding.addFoodButton.hide()
        }
    }


    private fun setMealsAdapter(
        mealList: List<Meal>,
        restaurantId: String,
        restaurantName: String
    ) {
        adapter.setMealList(mealList, restaurantId, restaurantName)
        binding.mealsRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}