package com.example.moonproject.Models

import android.content.Context

class PreferencesManager(context: Context) {

    // Define the name of the SharedPreferences file
    private val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    // Function to save a string value
    fun saveString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply() // or commit() for synchronous saving
        }
    }

    // Function to retrieve a string value
    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    // Function to save an integer value
    fun saveInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            apply()
        }
    }

    // Function to retrieve an integer value
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    // Function to clear specific preference
    fun clearPreference(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }

    // Function to clear all preferences
    fun clearAllPreferences() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}
