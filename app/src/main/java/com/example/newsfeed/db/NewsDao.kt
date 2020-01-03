package com.example.newsfeed.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {

    @Query("SELECT * from news_feed")
    fun getNewsFeed(): LiveData<List<NewsFeedModelEntity>>

    @Query("SELECT * from news_feed WHERE _id==:id")
    fun getNewsDetails(id:Long ):LiveData<NewsFeedModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: NewsFeedModelEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<NewsFeedModelEntity>)

    @Query("DELETE from news_feed")
    fun deleteAll()
}