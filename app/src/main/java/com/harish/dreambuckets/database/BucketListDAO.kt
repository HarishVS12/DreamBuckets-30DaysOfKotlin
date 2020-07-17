package com.harish.dreambuckets.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BucketListDAO {

    @Insert
    suspend fun insert(bucketList: BucketList)

    @Query("Select * from bucket_list Order By dream_level ASC")
     fun getBuckets() : LiveData<List<BucketList>>

    @Query("Select * from bucket_list where bucket_category=:category Order by dream_level ASC")
    fun getBucketsByCategory(category: String) : LiveData<List<BucketList>>

    @Query("Select * from bucket_list where id=:bucketID")
    fun getBucketsById(bucketID: Int) : LiveData<BucketList>

    @Query("Delete from bucket_list where id=:id")
    suspend fun deleteBucket(id: Int)

    @Query("Update bucket_list SET isAccompolished=:isAccompolished Where id=:id")
    suspend fun updateAccompolished(isAccompolished:Int,id: Int)

    @Query("Select * from bucket_list where isAccompolished=:isAccompolished")
    fun getBucketsByAccompolish(isAccompolished: Int):LiveData<List<BucketList>>

}