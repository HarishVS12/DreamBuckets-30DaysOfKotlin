package com.harish.dreambuckets.ui.activities

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityBucketAddBinding
import com.harish.dreambuckets.viewmodels.BucketListViewModel

@RequiresApi(Build.VERSION_CODES.M)
class BucketAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBucketAddBinding
    private  var categoryTitle:String?  = ""
    private lateinit var picker: MaterialDatePicker<Long>
    private lateinit var imageUriString: String



    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransition()
        super.onCreate(savedInstanceState)
        clearLightStatusBar(window.decorView)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bucket_add)
        if(DashboardActivity.isNightMode){
            binding.toolbar.setNavigationIcon(R.drawable.ic_back_night)
        }else{
            binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        }


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        //This can be called before an activity gets created but go down
        val openDocument = registerForActivityResult(ActivityResultContracts.OpenDocument()){
                uri: Uri? ->
            binding.imageView.setImageURI(uri)
            imageUriString = uri.toString()
            binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }



        binding.addPhotoFAB.setOnClickListener {
            //This can only be called once the activity gets created
            openDocument.launch(Array<String>(1){"image/*"})
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
                viewModel.livedate.value!!,
                imageUriString
             )
            Toast.makeText(this, "Created Successfully!!", Toast.LENGTH_SHORT).show()
            finish()

        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkIfEmpty(name:String, thoughts:String, chipChecked:Boolean, date:String):Boolean{
        if(name.isNullOrEmpty() || thoughts.isNullOrEmpty() || !chipChecked || date.isNullOrEmpty() || imageUriString.isNullOrEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()

            val animator = ObjectAnimator.ofArgb(binding.createBucketButton, "backgroundColor", getColor(R.color.chipBGColor), getColor(R.color.errorColorNight))
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

        window.sharedElementExitTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }

        window.sharedElementReenterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }

    }

    fun clearLightStatusBar(@NonNull view: View) {
        var flags = view.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        view.systemUiVisibility = flags
    }



}