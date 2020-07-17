package com.harish.dreambuckets.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.ActivityDetailedBucketBinding
import com.harish.dreambuckets.viewmodels.BucketListViewModel

class DetailedBucketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedBucketBinding
    private lateinit var bucketListViewModel: BucketListViewModel
    private var bucketID: Int = -1
    private var accompolishID:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedElementTransition()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detailed_bucket)

        bucketID = intent.getIntExtra("bucketID",0)
        accompolishID = intent.getIntExtra("AccompolishID",0)

        binding.backButtonImageView.setOnClickListener{
            onBackPressed()
        }

        if(accompolishID==0)
            binding.accompolishMaterialButton.visibility = View.VISIBLE
        else
            binding.accompolishMaterialButton.visibility = View.INVISIBLE


        bucketListViewModel = ViewModelProvider(this).get(BucketListViewModel::class.java)

        bucketListViewModel.getBucketsById(bucketID).observe(this, Observer {
            binding.apply {
                titleTextView.text = it.bucketName
                targetDateAnsTextView.text = it.bucketTargetDate
                categoryAnsTextView.text = it.category
                thoughtsTextView.text = it.bucketThoughts
                when(it.dreamLevel){
                    "Level 1"-> cardColorImageView.setImageResource(R.color.levelcolor1)
                    "Level 2"-> cardColorImageView.setImageResource(R.color.levelcolor2)
                    "Level 3"-> cardColorImageView.setImageResource(R.color.levelcolor3)
                }

                Glide
                    .with(this@DetailedBucketActivity)
                    .load(it.bucketImageUri)
                    .centerCrop()
                    .into(cardImageView)
            }
        })



        binding.accompolishMaterialButton.setOnClickListener {
            bucketListViewModel.updateBucketByAccompolish(1,bucketID)
            Toast.makeText(this, "Successfully Accompolished!!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun sharedElementTransition() {
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
}