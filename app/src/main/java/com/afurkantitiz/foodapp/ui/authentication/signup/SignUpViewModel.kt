package com.afurkantitiz.foodapp.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.signup.SignUpRequest
import com.afurkantitiz.foodapp.data.entity.signup.SignUpResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {
    fun register(name: String, email: String, password: String, imageUrl: String): LiveData<Resource<SignUpResponse>> {
        val request = SignUpRequest(name, email, password)
        return apiRepository.signUp(request)
    }
}