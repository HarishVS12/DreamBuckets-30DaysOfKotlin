package com.harish.dreambuckets.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityDashboardBinding
import com.harish.dreambuckets.ui.BottomNavDrawerFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransition()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard)

        binding.appBar.performShow()

        navController = findNavController(R.id.navHostFragment)
        window.statusBarColor = Color.WHITE
        setSupportActionBar(binding.appBar)


        binding.appBar.setNavigationOnClickListener {
            var navDrawer  = BottomNavDrawerFragment()
            navDrawer.show(supportFragmentManager,"TAG")
        }

        binding.floatingAdd.setOnClickListener {
            val intent = Intent(this, BucketAddActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
                binding.floatingAdd,
                "shared_element_container"
            )
            startActivity(intent, options.toBundle())
        }



    }

    override fun onResumeFragments() {
        binding.appBar.performShow()
        super.onResumeFragments()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutFragment ->
                    findNavController(R.id.navHostFragment).navigate(R.id.aboutFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    fun sharedElementTransition(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
    }
}
