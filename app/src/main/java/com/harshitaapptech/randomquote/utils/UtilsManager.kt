package com.harshitaapptech.randomquote.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.harshitaapptech.randomquote.BuildConfig

object UtilsManager {

    private const val TAG = "UtilsManager"

    fun log(TAG: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, message)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //check connectivity
    @Suppress("DEPRECATION")
    fun Fragment.isInternetConnected(isConnected: (Boolean) -> Unit) {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // for android api 28 >
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return isConnected(true)
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return isConnected(true)
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return isConnected(true)
                    }
                }
            }
        } else {
            //for android api 28 <
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    log(TAG, "Network is available : true")
                    return isConnected(true)
                }
            } catch (e: Exception) {
                log(TAG, "" + e.message)
            }
        }
        log(TAG, "Network is available : FALSE ")
        return isConnected(false)
    }

    //check connectivity
    @Suppress("DEPRECATION")
    fun Activity.isInternetConnected(isConnected: (Boolean) -> Unit) {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // for android api 28 >
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return isConnected(true)
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return isConnected(true)
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return isConnected(true)
                    }
                }
            }
        } else {
            //for android api 28 <
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    log(TAG, "Network is available : true")
                    return isConnected(true)
                }
            } catch (e: Exception) {
                log(TAG, "" + e.message)
            }
        }
        log(TAG, "Network is available : FALSE ")
        return isConnected(false)
    }

    fun View.fadeVisibility(visibility: Int, duration: Long = 400) {
        val transition: Transition = Fade()
        transition.duration = duration
        transition.addTarget(this)
        TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
        this.visibility = visibility
    }

    //no internet dialog
    fun showAlertConnectionError(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle("No internet!")
        builder.setMessage("Internet is required to run this app")
        builder.setPositiveButton(
            "Ok"
        ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
        builder.show()
    }

}