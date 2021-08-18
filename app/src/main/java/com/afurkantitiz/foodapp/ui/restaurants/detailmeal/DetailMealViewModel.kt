package com.afurkantitiz.foodapp.ui.restaurants.detailmeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.meal.Meal
import com.afurkantitiz.foodapp.data.entity.meal.MealResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMealViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    var meal: Meal? = null

    fun getMealDetails(id: String): LiveData<Resource<MealResponse>> {
        return apiRepository.getMealById(id)
    }
}