package com.harish.dreambuckets.ui.activities


import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityDashboardBinding
import com.harish.dreambuckets.utilities.sharedElementTransitionExit


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navController:NavController

    companion object {
         var isNightMode: Boolean = false
    }
    private lateinit var darkModePrefEdit: SharedPreferences.Editor
    private lateinit var darkModePref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransitionExit(window,this)
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




}
