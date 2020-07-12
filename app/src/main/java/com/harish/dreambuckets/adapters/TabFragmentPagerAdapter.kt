package com.harish.dreambuckets.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.harish.dreambuckets.ui.tabs.DreamsTab
import com.harish.dreambuckets.ui.tabs.MemoriesTab

class TabFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    noOfTabs: Int):
    FragmentStatePagerAdapter(fragmentManager,noOfTabs) {


    override fun getItem(position: Int): Fragment {
        lateinit var frag : Fragment
        when(position){
            0 -> frag = DreamsTab()
            1-> frag =  MemoriesTab()
        }
        return frag
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        var str: CharSequence = ""
        when(position){
            0 -> str = "Dreams"
            1 -> str = "Memories"
        }
        return str
    }


}