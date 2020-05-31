package com.harish.dreambuckets.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BucketList::class], version =1, exportSchema = false)
public abstract class BucketListDatabase : RoomDatabase() {

    abstract fun getDao() : BucketListDAO

    companion object{

        @Volatile
        private var INSTANCE : BucketListDatabase? = null
        
        fun getDatabase(context: Context) : BucketListDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BucketListDatabase::class.java,
                    "bucket_list"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}