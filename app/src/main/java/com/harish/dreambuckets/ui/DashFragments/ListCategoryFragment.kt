package com.harish.dreambuckets.ui.DashFragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.HomeDisplayAdapter
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.databinding.FragmentListCategoryBinding
import com.harish.dreambuckets.viewmodels.BucketListViewModel

class ListCategoryFragment : Fragment() {

    private lateinit var binding: FragmentListCategoryBinding
    private lateinit var viewModel: BucketListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_category,
                                            container, false)

        viewModel = ViewModelProvider(this).get(BucketListViewModel::class.java)
        val args = ListCategoryFragmentArgs.fromBundle(requireArguments())

        val adapter = HomeDisplayAdapter(requireContext(),viewModel)

        viewModel.getBucketsByCategory(args.category).observe(requireActivity(), Observer {
                buckets ->
            buckets?.let {
                if(buckets.isEmpty())
                    binding.emptyAnimation.visibility = View.VISIBLE
                else
                    binding.emptyAnimation.visibility = View.GONE
                adapter.setWords(it as MutableList<BucketList>)
            }
        })

        binding.apply {

            listcategoryRecyclerView.adapter = adapter
            listcategoryRecyclerView.setHasFixedSize(true)
            listcategoryRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        }

        return binding.root
    }
}