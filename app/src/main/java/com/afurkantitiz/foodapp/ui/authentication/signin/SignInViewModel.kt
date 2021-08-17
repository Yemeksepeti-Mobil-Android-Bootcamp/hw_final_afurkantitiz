package com.afurkantitiz.foodapp.ui.authentication.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel(){

    fun signIn(email: String, password: String): LiveData<Resource<SignInResponse>> {
        val request = SignInRequest(email, password)
        return apiRepository.login(request)
    }
}