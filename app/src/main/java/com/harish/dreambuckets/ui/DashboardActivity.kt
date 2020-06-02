package com.harish.dreambuckets.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityDashboardBinding
import com.harish.dreambuckets.viewmodels.DashboardViewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard)
        navController = findNavController(R.id.navHostFragment)

        setSupportActionBar(binding.appBar)


        binding.appBar.setNavigationOnClickListener {
            var navDrawer  = BottomNavDrawerFragment()
            navDrawer.show(supportFragmentManager,"TAG")
        }

        binding.floatingAdd.setOnClickListener {
            startActivity(Intent(this, BucketAddActivity::class.java))
        }

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        binding.viewModel = viewModel


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
}
