package com.afurkantitiz.foodapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    companion object {
        const val TOKEN = "com.afurkantitiz.foodapp.TOKEN"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setOnboardShow() {
        sharedPreferences.edit().putBoolean("onboard", true).apply()
    }

    fun isOnboardShow(): Boolean {
        return sharedPreferences.getBoolean("onboard", false)
    }
}