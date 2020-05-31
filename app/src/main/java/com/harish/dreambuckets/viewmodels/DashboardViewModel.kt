package com.harish.dreambuckets.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {


    val names:String = "Harish is a good boy"

    private var _name = MutableLiveData<String>()



    private val name : LiveData<String>
        get() {
            _name.value = "Harish is a good boy"
            return _name
        }


    fun displayName():LiveData<String>{
        return name
    }

}