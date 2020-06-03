package com.harish.dreambuckets.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.harish.dreambuckets.R
import com.harish.dreambuckets.viewmodels.BucketListViewModel
import com.harish.dreambuckets.databinding.ActivityBucketAddBinding
import java.text.SimpleDateFormat
import java.util.*

class BucketAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBucketAddBinding
    private  var categoryTitle:String?  = ""
    private lateinit var picker: MaterialDatePicker<Long>
//    private lateinit var month_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bucket_add)
        val viewModel = ViewModelProvider(this).get(BucketListViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)


        binding.categoryChipGroup.setOnCheckedChangeListener {
                group, checkedId:Int ->
            val checkedChip: Chip? = findViewById(checkedId)
            checkedChip?.let {
                categoryTitle = checkedChip?.text.toString()
            }
        }

        binding.fixedDateTextView.setOnClickListener {

            picker = viewModel.pickerInit()
            picker.show(supportFragmentManager,picker.toString())

            picker.addOnPositiveButtonClickListener {
                    viewModel.pickerDateReceiver(it)
            }
        }



        binding.createBucketButton.setOnClickListener {

            if(checkIfEmpty(
                binding.nameEditText.text.toString(),
                binding.thoughtsEditText.text.toString(),
                binding.categoryChipGroup.isSingleSelection,
                binding.fixedDateTextView.text.toString()
            )){
                return@setOnClickListener
            }


            viewModel.insert(
                    binding.nameEditText.text.toString(),
                    binding.thoughtsEditText.text.toString(),
                    categoryTitle,
                    viewModel.livedate.value!!
            )
            Toast.makeText(this, "Created Successfully!!", Toast.LENGTH_SHORT).show()
            finish()

        }

    }

    fun checkIfEmpty(name:String, thoughts:String, chipChecked:Boolean, date:String):Boolean{
        if(name.isNullOrEmpty() || thoughts.isNullOrEmpty() || !chipChecked || date.isNullOrEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

}