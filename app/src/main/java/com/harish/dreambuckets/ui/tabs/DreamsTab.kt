package com.harish.dreambuckets.ui.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.HomeDisplayAdapter
import com.harish.dreambuckets.databinding.FragmentDreamsTabBinding
import com.harish.dreambuckets.ui.activities.DetailedBucketActivity
import com.harish.dreambuckets.utilities.SwipeToDelete
import com.harish.dreambuckets.viewmodels.BucketListViewModel

class DreamsTab : Fragment(), HomeDisplayAdapter.OnItemSelectedListener{

    private lateinit var binding: FragmentDreamsTabBinding
    private val accompolishedID: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dreams_tab,container, false)


        val viewModel = ViewModelProvider(requireActivity()).get(BucketListViewModel::class.java)

        val adapter = HomeDisplayAdapter(requireContext(), viewModel, this)

        viewModel.getBucketsByAccompolish(accompolishedID).observe(requireActivity(), androidx.lifecycle.Observer { buckets ->
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
                 val item = ItemTouchHelper(SwipeToDelete(adapter,requireActivity()))
                 item.attachToRecyclerView(homeRecyclerView)
             }

        return binding.root
    }

    override fun onItemSelected(position: Int, id:Int) {
        val intent = Intent(activity, DetailedBucketActivity::class.java)
        intent.putExtra("bucketID",id)
        intent.putExtra("AccompolishID",accompolishedID)
        startActivity(intent)
    }

}