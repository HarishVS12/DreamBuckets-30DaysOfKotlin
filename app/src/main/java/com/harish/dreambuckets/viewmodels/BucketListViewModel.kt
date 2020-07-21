package com.harish.dreambuckets.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.google.android.material.datepicker.MaterialDatePicker
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.database.BucketListDatabase
import com.harish.dreambuckets.database.BucketListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class BucketListViewModel(private val repository: BucketListRepository)
    : ViewModel() {



    val bucketLists: LiveData<List<BucketList>>
    lateinit var bucketListCategory : LiveData<List<BucketList>>

    init {
        bucketLists = repository.bucketlists
    }


    fun deleteBucket(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteBucket(id)
        }
    }


    fun getBucketsByAccompolish(isAccompolish: Int):LiveData<List<BucketList>>{
        return repository.getBucketsByAccompolish(isAccompolish)
    }

    fun getBucketsByCategory(category: String):LiveData<List<BucketList>> {
            return repository.getBucketsByCategory(category)
    }



}