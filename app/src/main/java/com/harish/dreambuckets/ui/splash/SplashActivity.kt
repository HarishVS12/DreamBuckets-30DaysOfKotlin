package com.harish.dreambuckets.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.harish.dreambuckets.R
import com.harish.dreambuckets.ui.activities.DashboardActivity

private const val DELAYED_MILLIS:Long = 2500

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }, DELAYED_MILLIS)

    }
}