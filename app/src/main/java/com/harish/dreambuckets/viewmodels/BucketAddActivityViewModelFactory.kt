package com.harish.dreambuckets.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harish.dreambuckets.database.BucketListRepository

class BucketAddActivityViewModelFactory(private val repository: BucketListRepository) :
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BucketAddActivityViewModel(repository) as T
    }

}