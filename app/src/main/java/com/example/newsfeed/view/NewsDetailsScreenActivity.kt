package com.example.newsfeed.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsfeed.R
import com.example.newsfeed.databinding.ActivityNewsDetailsBinding
import com.example.newsfeed.db.AppDataBaseManager
import com.example.newsfeed.util.getFont
import com.example.newsfeed.viewmodel.NewsFeedViewModel

class NewsDetailsScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNewsDetailsBinding
    private var position: Int = 0
    private lateinit var newsFeedViewModel: NewsFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        viewBinding.lifecycleOwner = this

        viewBinding.feedTittle?.typeface = this@NewsDetailsScreenActivity getFont "roboto_bold.ttf"
        viewBinding.feedSource?.typeface = this@NewsDetailsScreenActivity getFont "roboto_regular.ttf"
        viewBinding.feedDate?.typeface = this@NewsDetailsScreenActivity getFont "roboto_regular.ttf"
        viewBinding.newsDescription?.typeface = this@NewsDetailsScreenActivity getFont "roboto_regular.ttf"

        newsFeedViewModel = ViewModelProviders.of(this).get(NewsFeedViewModel::class.java)
        position = intent?.extras?.getInt("position", 0) ?: 0


        viewBinding.backImage.setOnClickListener {
            
            finish()
        }

        newsFeedViewModel.newsFeed.observe(this, Observer {
            if(!it.isNullOrEmpty()) {
                viewBinding.data = it[position]
            }
        })
        viewBinding.executePendingBindings()

    }

}