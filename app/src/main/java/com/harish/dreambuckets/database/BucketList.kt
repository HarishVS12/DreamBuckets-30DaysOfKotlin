package com.harish.dreambuckets.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "bucket_list")
data class BucketList(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "bucket_name")
    var bucketName: String,
    @ColumnInfo(name = "bucket_thoughts")
    var bucketThoughts : String,
    @ColumnInfo(name = "bucket_category")
    var category: String,
    @ColumnInfo(name = "traget_date")
    var bucketTargetDate: String
){
    @Ignore
    constructor(bucketName: String, bucketThoughts: String,
                bucketCategory: String, targetDate: String) : this(
            id=0, bucketName = bucketName,bucketThoughts = bucketThoughts,
        category = bucketCategory, bucketTargetDate = targetDate)


}
