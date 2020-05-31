package com.harish.dreambuckets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BucketListDAO {

    @Insert
    suspend fun insert(bucketList: BucketList)

    @Query("Select * from bucket_list")
     fun getBuckets() : LiveData<List<BucketList>>

}