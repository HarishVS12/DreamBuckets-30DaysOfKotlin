package com.harish.dreambuckets.database

import androidx.lifecycle.LiveData

class BucketListRepository(var bucketListDAO: BucketListDAO){

    var bucketlists: LiveData<List<BucketList>> = bucketListDAO.getBuckets()


     fun getBucketsByCategory(category:String):LiveData<List<BucketList>>{
        return bucketListDAO.getBucketsByCategory(category)
    }

    fun getBucketsById(bucketID:Int): LiveData<BucketList>{
        return bucketListDAO.getBucketsById(bucketID)
    }

    fun getBucketsByAccompolish(isAccompolish: Int):LiveData<List<BucketList>>{
        return bucketListDAO.getBucketsByAccompolish(isAccompolish)
    }

    suspend fun insertBuckets(buckets: BucketList){
        bucketListDAO.insert(buckets)
    }

    suspend fun deleteBucket(id: Int){
        bucketListDAO.deleteBucket(id)
    }

    suspend fun updateBucketByAccompolish(isAccompolish:Int, id: Int){
        bucketListDAO.updateAccompolished(isAccompolish,id)
    }

}