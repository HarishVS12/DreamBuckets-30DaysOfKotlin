package com.harish.dreambuckets.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.database.BucketListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DetailBucketsViewModel(private val repository: BucketListRepository)
    : ViewModel() {

    fun getBucketsById(bucketID: Int): LiveData<BucketList>{
        return repository.getBucketsById(bucketID)
    }

    fun updateBucketByAccompolish(isAccompolish:Int, id:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateBucketByAccompolish(isAccompolish,id)
        }
    }


}