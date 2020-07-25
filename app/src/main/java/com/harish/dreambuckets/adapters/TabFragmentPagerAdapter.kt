package com.harish.dreambuckets.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harish.dreambuckets.ui.tabs.DreamsTabFragment
import com.harish.dreambuckets.ui.tabs.MemoriesTabFragment

class TabFragmentPagerAdapter(
    fragment: Fragment):
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment : Fragment
        when(position){
            0 -> fragment = DreamsTabFragment()
            1 -> fragment = MemoriesTabFragment()
        }
        return fragment
    }


}