package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentListRestaurantsBinding
import com.synnapps.carouselview.ImageListener

class ListRestaurantsFragment : Fragment() {
    private var _binding: FragmentListRestaurantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantsList: ArrayList<Restaurant>
    private lateinit var carouselViewImagesList: ArrayList<Int>
    private lateinit var imageListener: ImageListener
    private lateinit var restaurantsAdapter: RestaurantsAdapter

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
    }

    private fun initViews() {
        carouselViewImagesList = arrayListOf()
        restaurantsList = arrayListOf()
        restaurantsAdapter = RestaurantsAdapter(restaurantsList, requireContext())

        setCarouselView()
    }

    private fun setFakeDataForAllRestaurants() {
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","30","dasdasdsa"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","20","dasdasdas"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","10","adsdsadas"))
        restaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food","40","adsdsadas"))
        setRecyclerViewForAllRestaurants()
    }

    private fun setRecyclerViewForAllRestaurants() {
        binding.listRestaurantAllRestaurantsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.listRestaurantAllRestaurantsRecyclerView.setHasFixedSize(true)
        binding.listRestaurantAllRestaurantsRecyclerView.adapter = restaurantsAdapter
    }

    private fun setCarouselView() {
        carouselViewImagesList.add(R.drawable.carousel_image1)
        carouselViewImagesList.add(R.drawable.carousel_image1)
        carouselViewImagesList.add(R.drawable.carousel_image1)

        binding.listRestaurantCarouselView.pageCount = carouselViewImagesList.size
        imageListener = ImageListener { position, imageView ->
            imageView.setImageResource(carouselViewImagesList[position])
        }
        binding.listRestaurantCarouselView.setImageListener(imageListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}