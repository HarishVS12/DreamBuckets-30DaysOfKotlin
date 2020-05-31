package com.harish.dreambuckets.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentBottomNavDrawerBinding

class BottomNavDrawerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomNavDrawerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_nav_drawer,
                                            container, false)

        setHasOptionsMenu(true)
        NavigationUI.setupWithNavController(binding.navigationView, findNavController())


        return binding.root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_appbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
    }

}