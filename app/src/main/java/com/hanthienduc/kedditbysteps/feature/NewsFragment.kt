package com.hanthienduc.kedditbysteps.feature

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanthienduc.kedditbysteps.KedditApp
import com.hanthienduc.kedditbysteps.R
import com.hanthienduc.kedditbysteps.commons.InfiniteScrollListener
import com.hanthienduc.kedditbysteps.commons.RedditNews
import com.hanthienduc.kedditbysteps.commons.RxBaseFragment
import com.hanthienduc.kedditbysteps.commons.extensions.inflate
import com.hanthienduc.kedditbysteps.feature.adapter.NewsAdapter
import com.hanthienduc.kedditbysteps.feature.adapter.NewsDelegateAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by hanth on 22/05/2017.
 */

class NewsFragment : RxBaseFragment(), NewsDelegateAdapter.onViewSelectedListener {
    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(news_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    @Inject lateinit var newsManager: NewsManager
    private var redditNews: RedditNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KedditApp.newsComponent.inject(this)
    }



    private val newsList: RecyclerView by lazy {
        //view?.findViewById(R.id.news_list) as RecyclerView old
        news_list
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.news_fragment)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // news_list.setHasFixedSize(true)
        // news_list.layoutManager = LinearLayoutManager(context)
        news_list.apply {
            setHasFixedSize(true)
            val linearlayout = LinearLayoutManager(context)
            layoutManager = linearlayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearlayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState?.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }



    private fun requestNews() {
        /**
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the 'after' param.
         */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }


    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter(this)
        }
    }


}
