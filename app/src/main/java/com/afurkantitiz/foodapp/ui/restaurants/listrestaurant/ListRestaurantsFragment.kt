package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.restaurant.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentListRestaurantsBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListRestaurantsFragment : Fragment() {
    private var _binding: FragmentListRestaurantsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListRestaurantsViewModel by viewModels()

    private var restaurantsAdapter = RestaurantsAdapter()
    private var cuisineList: HashMap<String, MaterialButton> = hashMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRestaurantRestaurantsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        getRestaurantsForAPI()
        setCuisineList()
    }

    private fun getRestaurantsForAPI(){
        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> binding.progressBar.show()

                Resource.Status.SUCCESS -> {
                    viewModel.restaurantList = it.data?.restaurantList
                    setRestaurants(viewModel.restaurantList)
                }

                Resource.Status.ERROR -> binding.progressBar.show()
            }
        })
    }

    private fun setRestaurants(restaurantList: List<Restaurant>?) {
        isRestaurantListVisible(restaurantList.isNullOrEmpty().not())
        restaurantsAdapter.setData(restaurantList)
        binding.listRestaurantRestaurantsRecyclerView.adapter = restaurantsAdapter
    }

    private fun setCuisineList() {
        val list = resources.getStringArray(R.array.Cuisines).toMutableList()
        list.add(0, "All")

        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 80, 0)

        list.forEachIndexed { index, item ->
            val button = MaterialButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            button.text = item
            button.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (index == 0)
                        R.color.red
                    else
                        R.color.gray1
                )
            )
            button.layoutParams = params
            button.isAllCaps = false
            binding.cuisineTypeLinearLayout.addView(button)
            cuisineList[item] = button
        }
        addCuisineTypesListener()
    }

    private fun addCuisineTypesListener() {
        cuisineList.forEach { cuisine ->
            cuisine.value.setOnClickListener {
                //clear other text color
                cuisineList.values.forEach { cuisine ->
                    cuisine.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray
                        )
                    )
                }
                //make orange selected text
                cuisine.value.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                binding.listRestaurantSearchView.queryHint = "Search in ${cuisine.key}"
                binding.listRestaurantSearchView.onActionViewCollapsed()
                if (cuisine.key == "All")
                    getRestaurantsForAPI()
                else
                    sendCuisineRequest(cuisine.key)
            }
        }
    }

    private fun sendCuisineRequest(cuisineName: String) {
        viewModel.getRestaurantByCuisine(cuisineName).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurants(response.data?.restaurantList)
                }
                Resource.Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }

    private fun isRestaurantListVisible(isVisible: Boolean) {
        binding.progressBar.gone()
        binding.listRestaurantRestaurantsRecyclerView.isVisible = isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}