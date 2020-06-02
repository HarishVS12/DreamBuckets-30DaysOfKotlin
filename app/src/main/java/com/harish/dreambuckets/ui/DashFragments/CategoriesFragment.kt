package com.harish.dreambuckets.ui.DashFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.CategoriesAdapter
import com.harish.dreambuckets.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment(), CategoriesAdapter.OnCategoryClickListener {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoryArray: ArrayList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_categories,container,false)

        categoryArray = arrayListOf<String>("Travel","Life","Love","Food","Work","Passion")
        val categoryAdapter = CategoriesAdapter(categoryArray,this)


        binding.apply {

            rvCategoryRecyclerView.adapter = categoryAdapter
            rvCategoryRecyclerView.setHasFixedSize(true)
            rvCategoryRecyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
        }

        return binding.root
    }

    override fun onClickCatListener(position: Int) {
        val tempStr = categoryArray[position]

        findNavController().navigate(CategoriesFragmentDirections
            .actionCategoriesFragmentToListCategoryFragment(tempStr))
    }

}