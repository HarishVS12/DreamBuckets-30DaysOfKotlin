package com.harish.dreambuckets.utilities

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.adapters.HomeDisplayAdapter
import com.harish.dreambuckets.viewmodels.BucketListViewModel

class SwipeToDelete(adapter:HomeDisplayAdapter) : ItemTouchHelper.SimpleCallback(0,
                            ItemTouchHelper.LEFT){



     private var adapterHome : HomeDisplayAdapter = adapter

     override fun onMove(
         recyclerView: RecyclerView,
         viewHolder: RecyclerView.ViewHolder,
         target: RecyclerView.ViewHolder
     ): Boolean {
         TODO("Not yet implemented")
     }

     override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
         val position = viewHolder.adapterPosition
         adapterHome.deleteItem(position)
     }


 }