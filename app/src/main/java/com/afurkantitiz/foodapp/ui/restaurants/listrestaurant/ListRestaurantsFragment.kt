package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentListRestaurantsBinding
import com.synnapps.carouselview.ImageListener

class ListRestaurantsFragment : Fragment() {
    private var _binding: FragmentListRestaurantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularRestaurantsList: ArrayList<Restaurant>
    private lateinit var carouselViewImages: ArrayList<Int>
    private lateinit var imageListener: ImageListener
    private lateinit var popularRestaurantsAdapter: PopularRestaurantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRestaurantsBinding.inflate(inflater, container, false)

        initViews()
        setCarouselView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFakeDataForPopularRestaurants()
    }

    private fun initViews() {
        carouselViewImages = arrayListOf()
        popularRestaurantsList = arrayListOf()
        popularRestaurantsAdapter = PopularRestaurantsAdapter(popularRestaurantsList, requireContext())
    }

    private fun setFakeDataForPopularRestaurants() {
        popularRestaurantsList.add(Restaurant("https://via.placeholder.com/150", "Vegan Resto"))
        popularRestaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food"))
        popularRestaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Vegan Resto"))
        popularRestaurantsList.add(Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg", "Healty Food"))
        setRecyclerViewForPopularRestaurants()
    }

    private fun setRecyclerViewForPopularRestaurants() {
        binding.listRestaurantPopularRestaurantsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.listRestaurantPopularRestaurantsRecyclerView.adapter = popularRestaurantsAdapter
    }

    private fun setCarouselView() {
        carouselViewImages.add(R.drawable.carousel_image1)
        carouselViewImages.add(R.drawable.carousel_image1)
        carouselViewImages.add(R.drawable.carousel_image1)

        binding.listRestaurantCarouselView.pageCount = carouselViewImages.size
        imageListener = ImageListener { position, imageView ->
            imageView.setImageResource(carouselViewImages[position])
        }
        binding.listRestaurantCarouselView.setImageListener(imageListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}