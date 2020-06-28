package com.harish.dreambuckets.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.harish.dreambuckets.R
import com.harish.dreambuckets.ui.activities.DashboardActivity

private const val DELAYED_MILLIS:Long = 2500

class SplashActivity : AppCompatActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.secCardColor)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed(Runnable {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }, DELAYED_MILLIS)

    }
}