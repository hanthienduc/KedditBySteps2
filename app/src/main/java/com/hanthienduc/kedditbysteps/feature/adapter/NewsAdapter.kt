package com.hanthienduc.kedditbysteps.feature.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hanthienduc.kedditbysteps.commons.RedditNewsItem
import com.hanthienduc.kedditbysteps.commons.adapter.AdapterConstants
import com.hanthienduc.kedditbysteps.commons.adapter.ViewType
import com.hanthienduc.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter

/**
 * Created by hanth on 22/05/2017.
 */
class NewsAdapter(listener: NewsDelegateAdapter.onViewSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delagateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING

    }

    init {
        delagateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delagateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return delagateAdapters.get(viewType).onCreateViewHolder(parent!!)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delagateAdapters.get(getItemViewType(position)).onBindViewHolder(holder!!, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemChanged(initPosition, items.size + 1 /* plus loading item*/)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeChanged(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(0, items.size)
    }

    fun getNews(): List<RedditNewsItem> {
        return items.filter { it.getViewType() == AdapterConstants.NEWS }
                .map { it as RedditNewsItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}