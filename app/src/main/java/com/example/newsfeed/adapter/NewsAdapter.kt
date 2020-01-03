package com.example.newsfeed.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.databinding.ItemNewsBinding
import com.example.newsfeed.db.NewsFeedModelEntity
import com.example.newsfeed.util.getFont
import com.example.newsfeed.viewmodel.NewsFeedViewModel

class NewsAdapter(
    val context: Context,val  newsFeedViewModel: NewsFeedViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsList: MutableList<NewsFeedModelEntity>? = null

    init {
        newsList= ArrayList()
    }


    fun addItems(list: List<NewsFeedModelEntity>) {
        newsList?.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val liveDataViewBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        liveDataViewBinding.title.typeface = context getFont "roboto_regular.ttf"
        liveDataViewBinding.sourceText.typeface = context getFont "roboto_bold.ttf"

        return NewsViewHolder(liveDataViewBinding)
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }


    inner class NewsViewHolder(val view: ItemNewsBinding) : RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as NewsViewHolder
        holder.view.data = newsList!![position]
        holder.view.executePendingBindings()

        holder.view.itemCardView.setOnClickListener {
            newsFeedViewModel.selectedFeedPosition.value=position
            //newsFeedViewModel.selectedFeedData?.value = newsList!![position]
        }


    }

}
