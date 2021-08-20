package com.afurkantitiz.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.data.entity.restaurant.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentListRestaurantsBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsFragment : Fragment(), ICategoriesOnClick {
    private var _binding: FragmentListRestaurantsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantsViewModel by viewModels()
    private var restaurantsAdapter = RestaurantsAdapter()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRestaurantsBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRestaurants()
    }

    private fun initViews() {
        categoriesAdapter = CategoriesAdapter(viewModel.getCategories(), requireContext())
        categoriesAdapter.addListener(this)

        binding.listRestaurantRestaurantsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.setHasFixedSize(true)
        binding.categoryRecyclerView.adapter = categoriesAdapter
    }

    private fun getRestaurants(){
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

    private fun getCategoriesByRestaurants(currentPosition: Int) {
        if (currentPosition == 0){
            getRestaurants()
        }else {
            viewModel.getRestaurantByCuisine(viewModel.getCategories()[currentPosition].categoryName).observe(viewLifecycleOwner, { response ->
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
    }

    private fun setRestaurants(restaurantList: List<Restaurant>?) {
        isRestaurantListVisible(restaurantList.isNullOrEmpty().not())
        restaurantsAdapter.setData(restaurantList)
        binding.listRestaurantRestaurantsRecyclerView.adapter = restaurantsAdapter
    }

    private fun isRestaurantListVisible(isVisible: Boolean) {
        binding.progressBar.gone()
        binding.listRestaurantRestaurantsRecyclerView.isVisible = isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        getCategoriesByRestaurants(position)
    }
}