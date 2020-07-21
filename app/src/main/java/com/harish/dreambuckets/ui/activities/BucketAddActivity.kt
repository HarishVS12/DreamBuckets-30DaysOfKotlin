package com.harish.dreambuckets.ui.activities

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.DreamLevel
import com.harish.dreambuckets.adapters.DreamLevelAdapter
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.databinding.ActivityBucketAddBinding
import com.harish.dreambuckets.utilities.InjectorUtils
import com.harish.dreambuckets.utilities.pickerInit
import com.harish.dreambuckets.viewmodels.BucketAddActivityViewModel

private const val PICK_IMAGE = 1

@RequiresApi(Build.VERSION_CODES.M)
class BucketAddActivity : AppCompatActivity(), DreamLevelAdapter.OnLevelSelectListener {

    private lateinit var binding: ActivityBucketAddBinding
    private var categoryTitle: String? = ""
    private lateinit var picker: MaterialDatePicker<Long>
    private var imageUriString: String = ""
    private lateinit var dreamLevelArrayList: ArrayList<DreamLevel>
    private var dreamLevelString: String = ""
    private lateinit var dreamLevelAdapter: DreamLevelAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bucket_add)

        if (DashboardActivity.isNightMode) {
            window.statusBarColor = resources.getColor(R.color.addCardColor)
            binding.toolbar.setNavigationIcon(R.drawable.ic_back_night)
        } else {
            window.statusBarColor = Color.WHITE
            binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        }


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


     /*   val openDocument =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
                binding.imageView.setImageURI(uri)
                imageUriString = uri.toString()
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            }*/


        binding.addPhotoFAB.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_OPEN_DOCUMENT
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Pick a image"), PICK_IMAGE)
        }


        val factory = InjectorUtils.provideBucketAddActViewModel(this.applicationContext)
        val viewModel = ViewModelProvider(this, factory).get(BucketAddActivityViewModel::class.java)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)


        binding.categoryChipGroup.setOnCheckedChangeListener { group, checkedId: Int ->
            val checkedChip: Chip? = findViewById(checkedId)
            checkedChip?.let {
                categoryTitle = checkedChip?.text.toString()
            }
        }

        binding.fixedDateTextView.setOnClickListener {

            picker = pickerInit()
            picker.show(supportFragmentManager, picker.toString())

            picker.addOnPositiveButtonClickListener {
                viewModel.pickerDateReceiver(it)
            }
        }



        binding.createBucketButton.setOnClickListener {

            if (checkIfEmpty(
                    binding.nameEditText.text.toString(),
                    binding.thoughtsEditText.text.toString(),
                    binding.categoryChipGroup.isSingleSelection,
                    binding.fixedDateTextView.text.toString()
                )
            ) {
                return@setOnClickListener
            }

            val bucketList = BucketList(
                binding.nameEditText.text.toString(),
                binding.thoughtsEditText.text.toString(),
                categoryTitle!!,
                viewModel.livedate.value!!,
                imageUriString,
                dreamLevelString,
                0
            )

            viewModel.insert(bucketList)
            Toast.makeText(this, "Created Successfully!!", Toast.LENGTH_SHORT).show()
            finish()

        }

        dreamLevelArrayList = arrayListOf()
        dreamLevelArrayList.add(DreamLevel("Level 1", R.color.levelcolor1, false))
        dreamLevelArrayList.add(DreamLevel("Level 2", R.color.levelcolor2, false))
        dreamLevelArrayList.add(DreamLevel("Level 3", R.color.levelcolor3, false))

        dreamLevelAdapter = DreamLevelAdapter(dreamLevelArrayList, this, this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.dreamLevelRecyclerView.adapter = dreamLevelAdapter
        binding.dreamLevelRecyclerView.layoutManager = linearLayoutManager


    }

    override fun onLevelSelected(position: Int, isChecked: Boolean) {
        if(isChecked) {
            dreamLevelString = dreamLevelArrayList[position].levelName
        }else{
            dreamLevelString = ""
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            binding.imageView.setImageURI(data?.data)
            imageUriString = data?.data.toString()
            binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkIfEmpty(name: String, thoughts: String, chipChecked: Boolean, date: String): Boolean {
        if (name.isNullOrEmpty() || thoughts.isNullOrEmpty() || !chipChecked || date.isNullOrEmpty() || imageUriString.isNullOrEmpty()
            || dreamLevelString.equals("")) {


            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()


            val animator = ObjectAnimator.ofArgb(
                binding.createBucketButton,
                "backgroundColor",
                getColor(R.color.chipBGColor),
                getColor(R.color.errorColorNight)
            )
            animator.duration = 500
            animator.repeatCount = 3
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.start()

            return true
        }
        return false
    }





}