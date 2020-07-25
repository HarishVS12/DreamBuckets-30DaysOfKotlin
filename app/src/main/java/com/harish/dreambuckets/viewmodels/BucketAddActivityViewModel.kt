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

class BucketAddActivityViewModel(private val repository: BucketListRepository)
    : ViewModel() {


    private val _livedate = MutableLiveData<String?>()
    val livedate: LiveData<String?>
        get() = _livedate

    fun pickerDateReceiver(it: Long?) {
        val date = Date(it!!)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        _livedate.value = format.format(date)
    }


    fun insert(bucketList: BucketList) {
        insertBucketLists(bucketList)
    }

    private fun insertBucketLists(bucketList: BucketList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertBuckets(bucketList)
    }



}