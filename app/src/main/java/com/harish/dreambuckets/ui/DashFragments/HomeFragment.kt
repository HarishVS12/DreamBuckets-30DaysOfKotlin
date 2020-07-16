package com.harish.dreambuckets.ui.DashFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.TabFragmentPagerAdapter
import com.harish.dreambuckets.databinding.FragmentHomeBinding
import com.harish.dreambuckets.ui.activities.BucketAddActivity
import com.harish.dreambuckets.ui.tabs.DreamsTab
import com.harish.dreambuckets.ui.tabs.MemoriesTab
import com.harish.dreambuckets.viewmodels.BucketListViewModel

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_home, container, false)

        binding.floatingAdd.setOnClickListener {
            val intent = Intent(activity, BucketAddActivity::class.java)
            startActivity(intent)
        }

        binding.homeviewPager.isUserInputEnabled = false

        val pagerAdapter = TabFragmentPagerAdapter(this)
        binding.homeviewPager.apply {
            adapter = pagerAdapter
        }


        TabLayoutMediator(binding.tabLayout,binding.homeviewPager){
            tab,position ->
                if(position==0) tab.text = "Dreams"
                else if(position==1) tab.text = "Memories"
        }.attach()


        return binding.root
    }



}