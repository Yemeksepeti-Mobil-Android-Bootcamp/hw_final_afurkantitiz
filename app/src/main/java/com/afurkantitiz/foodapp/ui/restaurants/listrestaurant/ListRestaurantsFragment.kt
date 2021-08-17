package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Categories
import com.afurkantitiz.foodapp.data.entity.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentListRestaurantsBinding
import com.synnapps.carouselview.ImageListener

class ListRestaurantsFragment : Fragment(),ICategoriesOnClick {
    private var _binding: FragmentListRestaurantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantsList: ArrayList<Restaurant>
    private lateinit var categoriesList: ArrayList<Categories>
    private lateinit var restaurantsAdapter: RestaurantsAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private var currentPositionalCategory: Int? = null

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
        setFakeDataForAllRestaurants()
        setFakeDataForCategories()
    }

    private fun initViews() {
        restaurantsList = arrayListOf()
        categoriesList = arrayListOf()
        restaurantsAdapter = RestaurantsAdapter(restaurantsList, requireContext())
        categoriesAdapter = CategoriesAdapter(categoriesList, requireContext())

        categoriesAdapter.addListener(this)
    }

    private fun setFakeDataForAllRestaurants() {
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","30","dasdasdsa", "4"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","20","dasdasdas","5"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","10","adsdsadas","3"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","40","adsdsadas","2"))
        setRecyclerViewForAllRestaurants()
    }

    private fun setFakeDataForCategories() {
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        categoriesList.add(Categories("Food","https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg"))
        setRecyclerViewForCategories()
    }

    private fun setRecyclerViewForCategories() {
        binding.listRestaurantCategoriesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.listRestaurantCategoriesRecyclerView.setHasFixedSize(true)
        binding.listRestaurantCategoriesRecyclerView.adapter = categoriesAdapter
    }

    private fun setRecyclerViewForAllRestaurants() {
        binding.listRestaurantRestaurantsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.listRestaurantRestaurantsRecyclerView.setHasFixedSize(true)
        binding.listRestaurantRestaurantsRecyclerView.adapter = restaurantsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        currentPositionalCategory = position
    }
}