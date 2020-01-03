package com.example.newsfeed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.db.AppDataBaseManager
import com.example.newsfeed.view.AppRepository

class NewsFeedViewModel(app: Application): AndroidViewModel(app) {

    val selectedFeedPosition: MutableLiveData<Int> = MutableLiveData()

    val newsFeed = AppDataBaseManager.db.getNewsDao().getNewsFeed()

    val repository = AppRepository(app)

    fun updateFeedItems() {
        repository.updateNewsFeed()
    }
}