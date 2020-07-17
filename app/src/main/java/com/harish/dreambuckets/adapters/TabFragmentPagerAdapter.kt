package com.harish.dreambuckets.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harish.dreambuckets.ui.tabs.DreamsTab
import com.harish.dreambuckets.ui.tabs.MemoriesTab

class TabFragmentPagerAdapter(
    fragment: Fragment):
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment : Fragment
        when(position){
            0 -> fragment = DreamsTab()
            1 -> fragment = MemoriesTab()
        }
        return fragment
    }


}