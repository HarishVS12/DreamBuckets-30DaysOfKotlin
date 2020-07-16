package com.harish.dreambuckets.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.database.BucketListDatabase
import com.harish.dreambuckets.database.BucketListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class BucketListViewModel(context: Application) : AndroidViewModel(context) {

    private val repository: BucketListRepository

    private val _livedate = MutableLiveData<String?>()
    val livedate: LiveData<String?>
            get() = _livedate


    val bucketLists: LiveData<List<BucketList>>
    lateinit var bucketListCategory : LiveData<List<BucketList>>

    init {

        val bucketsDao = BucketListDatabase.getDatabase(
            context
        ).getDao()
        repository =
            BucketListRepository(bucketsDao)
        bucketLists = repository.bucketlists

    }


    fun insert(bucketList: BucketList) {
        insertBucketLists(bucketList)
    }

    fun deleteBucket(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteBucket(id)
        }
    }

    fun updateBucketByAccompolish(isAccompolish:Int, id:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateBucketByAccompolish(isAccompolish,id)
        }
    }

    fun getBucketsByAccompolish(isAccompolish: Int):LiveData<List<BucketList>>{
        return repository.getBucketsByAccompolish(isAccompolish)
    }

    fun getBucketsByCategory(category: String):LiveData<List<BucketList>> {
            return repository.getBucketsByCategory(category)
    }

    fun getBucketsById(bucketID: Int): LiveData<BucketList>{
        return repository.getBucketsById(bucketID)
    }

    private fun insertBucketLists(bucketList: BucketList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertBuckets(bucketList)
    }

    fun pickerInit(): MaterialDatePicker<Long> {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        return picker
    }

    fun pickerDateReceiver(it: Long?) {
        val date = Date(it!!)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        _livedate.value = format.format(date)

    }

}