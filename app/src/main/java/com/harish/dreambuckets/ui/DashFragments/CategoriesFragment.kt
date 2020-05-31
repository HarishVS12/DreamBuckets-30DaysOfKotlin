package com.harish.dreambuckets.ui.DashFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.CategoriesAdapter
import com.harish.dreambuckets.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_categories,container,false)

        var a = arrayListOf<String>("Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka","Harish","Jumbalaka")
        val asa = CategoriesAdapter(a)


        binding.apply {

            rvCategoryRecyclerView.adapter = asa
            rvCategoryRecyclerView.setHasFixedSize(true)
            rvCategoryRecyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
        }

        return binding.root
    }

}