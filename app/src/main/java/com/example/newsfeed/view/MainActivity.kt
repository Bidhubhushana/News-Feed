package com.example.newsfeed.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.util.Pair as UtilPair
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsfeed.R
import com.example.newsfeed.adapter.NewsAdapter
import com.example.newsfeed.databinding.ActivityMainBinding
import com.example.newsfeed.util.getFont
import com.example.newsfeed.viewmodel.NewsFeedViewModel

class MainActivity : AppCompatActivity(), NewsAdapter.OnClick {

    private var activityBinding: ActivityMainBinding? = null
    private var adapter: NewsAdapter? = null

    private var newsFeedViewModel: NewsFeedViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        newsFeedViewModel = ViewModelProviders.of(this).get(NewsFeedViewModel::class.java)
        newsFeedViewModel?.updateFeedItems()

        activityBinding?.viewModel = newsFeedViewModel
        activityBinding?.lifecycleOwner = this


        adapter = NewsAdapter(this, newsFeedViewModel!!, this)
        activityBinding?.feedRecyclerView?.adapter = adapter


        activityBinding?.headline?.typeface = this@MainActivity getFont "roboto_bold.ttf"

        activityBinding?.executePendingBindings()

    }

    override fun onClick(imageView: ImageView,position: Int) {
        val intentSender = Intent(this, NewsDetailsScreenActivity::class.java)
        intentSender.putExtra("position", position)
        val imgPair = UtilPair.create(imageView as View, imageView.transitionName)
        val opt = ActivityOptions.makeSceneTransitionAnimation(this, imgPair)
        startActivity(intentSender, opt.toBundle())
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
