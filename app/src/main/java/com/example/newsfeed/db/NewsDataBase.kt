package com.example.newsfeed.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsfeed.pojo.NewsHeadline
import com.example.newsfeed.pojo.NewsTittles

@Database(entities = [NewsFeedModelEntity::class],version = 1)

abstract class NewsDataBase:RoomDatabase() {

    abstract fun  getNewsDao():NewsDao

}