package com.harish.dreambuckets.database

import androidx.lifecycle.LiveData

class BucketListRepository(var bucketListDAO: BucketListDAO){

    var bucketlists: LiveData<List<BucketList>> = bucketListDAO.getBuckets()


     fun getBucketsByCategory(category:String):LiveData<List<BucketList>>{
        return bucketListDAO.getBucketsByCategory(category)
    }


    suspend fun insertBuckets(buckets: BucketList){
        bucketListDAO.insert(buckets)
    }
}