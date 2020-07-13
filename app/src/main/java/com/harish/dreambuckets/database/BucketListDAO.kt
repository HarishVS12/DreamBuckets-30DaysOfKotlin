package com.harish.dreambuckets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BucketListDAO {

    @Insert
    suspend fun insert(bucketList: BucketList)

    @Query("Select * from bucket_list Order By dream_level ASC")
     fun getBuckets() : LiveData<List<BucketList>>

    @Query("Select * from bucket_list where bucket_category=:category Order by dream_level ASC")
    fun getBucketsByCategory(category: String) : LiveData<List<BucketList>>

    @Query("Delete from bucket_list where id=:id")
    suspend fun deleteBucket(id: Int)

}