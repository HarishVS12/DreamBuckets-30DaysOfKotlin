package com.harish.dreambuckets.ui.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentDreamsTabBinding
import com.harish.dreambuckets.databinding.FragmentMemoriesTabBinding

class MemoriesTab : Fragment() {

    private lateinit var binding: FragmentMemoriesTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_memories_tab,container, false)


        return binding.root
    }

}