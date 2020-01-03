package com.example.newsfeed.db

import androidx.room.Room
import com.example.newsfeed.ApplicationContext

object AppDataBaseManager {

    private const val DATABASE_NAME = "news_db"

    val db = Room.databaseBuilder(
        ApplicationContext().getAppContext(),
        NewsDataBase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()


}