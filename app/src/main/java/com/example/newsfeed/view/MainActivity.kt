package com.example.newsfeed.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsfeed.R
import com.example.newsfeed.adapter.NewsAdapter
import com.example.newsfeed.databinding.ActivityMainBinding
import com.example.newsfeed.util.getFont
import com.example.newsfeed.util.gone
import com.example.newsfeed.util.visible
import com.example.newsfeed.viewmodel.NewsFeedViewModel

class MainActivity : AppCompatActivity() {

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


        adapter = NewsAdapter(this, newsFeedViewModel!!)
        activityBinding?.feedRecyclerView?.adapter = adapter


        activityBinding?.headline?.typeface = this@MainActivity getFont "roboto_bold.ttf"

        setSelectedItemListener()
        activityBinding?.executePendingBindings()

    }


    private fun setSelectedItemListener() {

        newsFeedViewModel!!.selectedFeedPosition.observe(this, Observer {
            if (it != null) {
                val intentSender = Intent(this, NewsDetailsScreenActivity::class.java)
                intentSender.putExtra("position", it)
                startActivity(intentSender)

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        activityBinding!!.fragmentContainer.gone()
        activityBinding!!.feedRecyclerView.visible()
    }
}
