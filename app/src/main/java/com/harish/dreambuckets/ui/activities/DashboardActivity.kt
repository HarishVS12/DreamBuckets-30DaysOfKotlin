package com.harish.dreambuckets.ui.activities

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityDashboardBinding
import com.harish.dreambuckets.ui.DashFragments.HomeFragmentDirections


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navController:NavController

    companion object {
         var isNightMode: Boolean = false
    }
    private lateinit var darkModePrefEdit: SharedPreferences.Editor
    private lateinit var darkModePref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransition()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard)
        navController = findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding.bottomNavView,navController)

          darkModePref = getSharedPreferences("DarkModePref",0)
          darkModePrefEdit = darkModePref.edit()
          isNightMode = darkModePref.getBoolean("NightMode",false)

        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        setSupportActionBar(binding.toolbar)


       /* binding.appBar.setNavigationOnClickListener {
            var navDrawer  = BottomNavDrawerFragment()
            navDrawer.show(supportFragmentManager,"TAG")
        }*/

      /*  binding.floatingAdd.setOnClickListener {
            val intent = Intent(this, BucketAddActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
                binding.floatingAdd,
                "shared_element_container"
            )
            startActivity(intent, options.toBundle())
        }
*/


    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutFragment ->
                    findNavController(R.id.navHostFragment).navigate(R.id.aboutFragment)
            R.id.darkModeMenu->{
                if(isNightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    darkModePrefEdit.putBoolean("NightMode",false)
                    darkModePrefEdit.apply()
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    darkModePrefEdit.putBoolean("NightMode", true)
                    darkModePrefEdit.apply()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun sharedElementTransition(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS) //This enables the transition to be performed on this activity
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
    }


}
