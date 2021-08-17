package com.afurkantitiz.foodapp.ui.restaurants.detailrestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.afurkantitiz.foodapp.data.entity.Meal
import com.afurkantitiz.foodapp.databinding.FragmentDetailRestaurantBinding

class DetailRestaurantFragment : Fragment() {
    private var _binding: FragmentDetailRestaurantBinding? = null
    private val binding get() = _binding!!

    private lateinit var mealList: ArrayList<Meal>
    private lateinit var restaurantDetailAdapter: DetailRestaurantsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRestaurantBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFakeDataForMeals()
    }

    private fun initViews() {
        mealList = arrayListOf()
        restaurantDetailAdapter = DetailRestaurantsAdapter(mealList, requireContext())
    }

    private fun setFakeDataForMeals() {
        mealList.add(Meal("https://static-40.sinclairstoryline.com/resources/media/6b7a7c7c-3c44-489c-9880-4a17508cdc6d-large16x9_Postworkout_meal.jpg?1577802416711",
"Deneme",
        "$25",
        "dene"))

        mealList.add(Meal("https://static-40.sinclairstoryline.com/resources/media/6b7a7c7c-3c44-489c-9880-4a17508cdc6d-large16x9_Postworkout_meal.jpg?1577802416711",
            "Deneme1",
            "$27",
            "dene"))

        mealList.add(Meal("https://static-40.sinclairstoryline.com/resources/media/6b7a7c7c-3c44-489c-9880-4a17508cdc6d-large16x9_Postworkout_meal.jpg?1577802416711",
            "Deneme2",
            "$26",
            "dene"))

        mealList.add(Meal("https://static-40.sinclairstoryline.com/resources/media/6b7a7c7c-3c44-489c-9880-4a17508cdc6d-large16x9_Postworkout_meal.jpg?1577802416711",
            "Deneme2",
            "$26",
            "dene"))

        mealList.add(Meal("https://static-40.sinclairstoryline.com/resources/media/6b7a7c7c-3c44-489c-9880-4a17508cdc6d-large16x9_Postworkout_meal.jpg?1577802416711",
            "Deneme2",
            "$26",
            "dene"))

        setRecyclerViewForMeals()
    }

    private fun setRecyclerViewForMeals() {
        binding.meailRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.meailRecyclerView.setHasFixedSize(true)
        binding.meailRecyclerView.adapter = restaurantDetailAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}