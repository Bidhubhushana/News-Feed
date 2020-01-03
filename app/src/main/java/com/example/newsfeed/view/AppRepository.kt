package com.example.newsfeed.view

import Retrofit2Manager
import android.app.Application
import android.util.Log
import com.example.newsfeed.ApplicationContext
import com.example.newsfeed.db.AppDataBaseManager
import com.example.newsfeed.db.NewsFeedModelEntity
import com.example.newsfeed.manager.ApiEndPoints
import com.example.newsfeed.manager.ApiService
import com.example.newsfeed.pojo.NewsHeadline
import com.example.newsfeed.pojo.NewsTittles
import com.example.newsfeed.util.toastMsg
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response


class AppRepository(val app: Application) : retrofit2.Callback<NewsHeadline> {

    private var newTitles: List<NewsTittles>? = null

    val apiService = Retrofit2Manager.getRetrofit().create(ApiService::class.java)


    fun updateNewsFeed() {
        val call = apiService.getNews(ApiEndPoints.URL)
        call.enqueue(this)
    }

    override fun onFailure(call: Call<NewsHeadline>, t: Throwable) {

        Log.d("error==", "error==$t")
        (app as ApplicationContext).toastMsg("No internet connection")
    }

    override fun onResponse(call: Call<NewsHeadline>, response: Response<NewsHeadline>) {
        Log.d("success==", "success==${response.body()?.articles}")
        Log.d("success==", "success==${response.body()?.status}")
        Log.d("success==", "success==${response.body()?.totalResults}")
        newTitles = response.body()?.articles

        val newsList: MutableList<NewsFeedModelEntity> = ArrayList()

        newTitles?.forEach {
            val newFeedEntity = NewsFeedModelEntity()
            newFeedEntity.author = it.author
            newFeedEntity.content = it.content
            newFeedEntity.description = it.description
            newFeedEntity.publishedAt = it.publishedAt
            newFeedEntity.title = it.title
            newFeedEntity.url = it.url
            newFeedEntity.urlToImage = it.urlToImage
            newFeedEntity.source = it.source?.name

            newsList.add(newFeedEntity)
        }

        Observable.fromCallable {
            AppDataBaseManager.db.getNewsDao().deleteAll()
            AppDataBaseManager.db.getNewsDao().insertAll(newsList)

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}