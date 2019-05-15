package com.tramalho.labs.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.Tweet
import kotlinx.android.synthetic.main.tweeter_row.view.*


class TweetAdapter(val tweets: ArrayList<Tweet>, private val action:(String) -> Unit) :
    RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.tweeter_row, p0, false)
        return TweetViewHolder(view, action)
    }

    override fun getItemCount() = tweets.size


    override fun onBindViewHolder(p0: TweetViewHolder, p1: Int) {
        p0.bind(tweets[p1])
    }


    class TweetViewHolder(val view: View, private val action:(String) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bind(tweet: Tweet) {
            itemView.textTweet.text = tweet.text
            itemView.dateTweet.text = tweet.createdAt
            itemView.setOnClickListener { action(tweet.text) }
        }
    }
}