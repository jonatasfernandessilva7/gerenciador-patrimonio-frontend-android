package com.example.gerenciadordepatrimonio.repository

import android.content.Context
import android.content.SharedPreferences

class PatrimonioRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
}
