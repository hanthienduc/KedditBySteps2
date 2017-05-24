package com.hanthienduc.kedditbysteps.feature.adapter

import com.hanthienduc.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.hanthienduc.kedditbysteps.commons.extensions.getFriendlyTime
import com.hanthienduc.kedditbysteps.commons.extensions.inflate
import com.hanthienduc.kedditbysteps.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*
/**
 * Created by hanth on 22/05/2017.
 */

class NewsDelegateAdapter(val viewActions: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup): android.support.v7.widget.RecyclerView.ViewHolder {
        return com.hanthienduc.kedditbysteps.feature.adapter.NewsDelegateAdapter.TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: android.support.v7.widget.RecyclerView.ViewHolder, item: com.hanthienduc.kedditbysteps.commons.adapter.ViewType) {
        holder as com.hanthienduc.kedditbysteps.feature.adapter.NewsDelegateAdapter.TurnsViewHolder
        holder.bind(item as com.hanthienduc.kedditbysteps.commons.RedditNewsItem)
    }

    class TurnsViewHolder(parent: android.view.ViewGroup) : android.support.v7.widget.RecyclerView.ViewHolder(
            parent.inflate(com.hanthienduc.kedditbysteps.R.layout.news_item)) {
        fun bind(item: com.hanthienduc.kedditbysteps.commons.RedditNewsItem) = with(itemView) {
            // Picasso.width(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()

        }
    }

}