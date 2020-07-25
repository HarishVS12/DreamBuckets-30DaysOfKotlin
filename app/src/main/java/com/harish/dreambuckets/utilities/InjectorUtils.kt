package com.harish.dreambuckets.utilities

import android.content.Context
import com.harish.dreambuckets.database.BucketListDatabase
import com.harish.dreambuckets.database.BucketListRepository
import com.harish.dreambuckets.viewmodels.BucketAddActivityViewModelFactory
import com.harish.dreambuckets.viewmodels.BucketListViewModelFactory
import com.harish.dreambuckets.viewmodels.DetailBucketsViewModelFactory

object InjectorUtils {

    private fun getBucketListRepository(context: Context): BucketListRepository{
        return BucketListRepository.getRepository(BucketListDatabase.getDatabase(context).getDao())
    }

    fun provideBucketListViewModel(context: Context): BucketListViewModelFactory{
        val repo = getBucketListRepository(context)
        return BucketListViewModelFactory(repo)
    }

    fun provideBucketAddActViewModel(context: Context):BucketAddActivityViewModelFactory{
        val repo = getBucketListRepository(context)
        return BucketAddActivityViewModelFactory(repo)
    }

    fun provideDetailedBucketViewModel(context: Context):DetailBucketsViewModelFactory{
        val repo = getBucketListRepository(context)
        return DetailBucketsViewModelFactory(repo)
    }
}