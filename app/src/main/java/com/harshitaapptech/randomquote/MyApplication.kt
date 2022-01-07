package com.harshitaapptech.randomquote

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.harshitaapptech.randomquote.utils.UtilsManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), LifecycleEventObserver {


    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    companion object {
        const val TAG = "MyApplication"
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_STOP -> {
                UtilsManager.log(TAG, "onStop of MyApplication: ")
            }
            Lifecycle.Event.ON_RESUME -> {
                UtilsManager.log(TAG, "onResume of MyApplication: ")
            }
            else -> {
                UtilsManager.log(TAG, "other state of MyApplication: ")
            }
        }
    }


}