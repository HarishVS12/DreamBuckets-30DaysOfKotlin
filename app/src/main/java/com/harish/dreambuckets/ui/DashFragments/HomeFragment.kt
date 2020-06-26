package com.harish.dreambuckets.ui.DashFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.HomeDisplayAdapter
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.viewmodels.BucketListViewModel
import com.harish.dreambuckets.databinding.FragmentHomeBinding
import com.harish.dreambuckets.ui.DashboardActivity
import com.harish.dreambuckets.utilities.SwipeToDelete
import java.util.Observer

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_home,container,false)

        val viewModel = ViewModelProvider(requireActivity()).get(BucketListViewModel::class.java)
//        a.add(HomeDisplayModel("30Days of Kotlin","I really have to finish this and want to get the certifiate as soon as possible"))



        val adapter = HomeDisplayAdapter()
        viewModel.bucketLists.observe(requireActivity(), androidx.lifecycle.Observer { buckets ->
            buckets?.let {
                if (buckets.isEmpty())
                    binding.emptyAnimation.visibility = View.VISIBLE
                else
                    binding.emptyAnimation.visibility = View.GONE
                adapter.setWords(it)
            }
        })



        binding.apply {

            homeRecyclerView.adapter = adapter
            homeRecyclerView.setHasFixedSize(true)
            homeRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            val item:ItemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter))
            item.attachToRecyclerView(homeRecyclerView)
        }

        return binding.root
    }


}