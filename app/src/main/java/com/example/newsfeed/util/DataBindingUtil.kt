package com.example.newsfeed.util

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfeed.adapter.NewsAdapter
import com.example.newsfeed.db.NewsFeedModelEntity

class DataBindingUtil {

    companion object{
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun setImageUrl(imageView:ImageView,url:String?){
            if(!url.isNullOrEmpty()) {
                Glide.with(imageView)
                    .load(url)
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("text")
        fun setText(textView: TextView, text: String?){
            if(!text.isNullOrEmpty()) {
                textView.text = text
            }
        }

        @JvmStatic
        @BindingAdapter("feedItems")
        fun setFeedItems(recyclerView: RecyclerView, items: LiveData<List<NewsFeedModelEntity>>?){
            val adapter =  recyclerView.adapter as NewsAdapter?
            if(adapter != null && !items?.value.isNullOrEmpty()) {
                adapter.addItems(items?.value!!)
            }
        }
    }
}