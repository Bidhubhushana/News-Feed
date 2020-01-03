package com.example.newsfeed.manager

import com.example.newsfeed.pojo.NewsHeadline
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET
    fun getNews(@Url url:String): Call<NewsHeadline>

}