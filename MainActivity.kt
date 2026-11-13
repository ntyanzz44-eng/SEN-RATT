package com.vionsix.apppinning

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)

        aktifkanPenyematanOtomatis()
    }

    override fun onResume() {
        super.onResume()
        aktifkanPenyematanOtomatis()
    }

    private fun aktifkanPenyematanOtomatis() {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activityManager.lockTaskModeState == ActivityManager.LOCK_TASK_MODE_NONE) {
                try {
                    startLockTask()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            @Suppress("DEPRECATION")
            if (!activityManager.isInLockTaskMode) {
                try {
                    startLockTask()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
    }
}
