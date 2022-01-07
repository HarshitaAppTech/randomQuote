package com.example.randomquote.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

/**
 * Created by Rahul Gaur on 11, December, 2019
 * Email: rahul@appwebstudios.com
 */
class PreferenceManager {

    private val editor: SharedPreferences.Editor
    private val sharedPreferences: SharedPreferences

    constructor(context: Context?) {
        sharedPreferences = context!!.getSharedPreferences(
            Constants.SHARED_PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        editor.apply()
    }

    constructor(fragment: Fragment) {
        sharedPreferences = fragment.requireContext()
            .getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    fun saveString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String {
        return sharedPreferences.getString(key, "")!!
    }

    private fun saveBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getBoolean(key: String?): Boolean {
        return try {
            sharedPreferences.getBoolean(key, false)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun clearPreferences() {
        editor.clear()
        editor.apply()
    }

}