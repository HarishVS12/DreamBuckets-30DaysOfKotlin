package com.harish.dreambuckets.ui.activities

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.harish.dreambuckets.R
import com.harish.dreambuckets.viewmodels.BucketListViewModel
import com.harish.dreambuckets.databinding.ActivityBucketAddBinding

class BucketAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBucketAddBinding
    private  var categoryTitle:String?  = ""
    private lateinit var picker: MaterialDatePicker<Long>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransition()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bucket_add)
        window.statusBarColor = Color.WHITE

        binding.backButtonImageView.setOnClickListener{
            finish()
        }

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

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkIfEmpty(name:String, thoughts:String, chipChecked:Boolean, date:String):Boolean{
        if(name.isNullOrEmpty() || thoughts.isNullOrEmpty() || !chipChecked || date.isNullOrEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()

            val animator = ObjectAnimator.ofArgb(binding.createBucketButton, "backgroundColor", getColor(R.color.secCardColor), Color.RED)
            animator.duration = 500
            animator.repeatCount = 3
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.start()

            return true
        }
        return false
    }

    fun sharedElementTransition(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }
    }

}