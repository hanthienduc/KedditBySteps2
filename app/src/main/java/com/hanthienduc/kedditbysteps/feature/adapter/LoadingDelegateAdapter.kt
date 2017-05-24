package com.hanthienduc.kedditbysteps.feature.adapter

import com.hanthienduc.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.hanthienduc.kedditbysteps.commons.extensions.inflate

/**
 * Created by hanth on 22/05/2017.
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onBindViewHolder(holder: android.support.v7.widget.RecyclerView.ViewHolder, item: com.hanthienduc.kedditbysteps.commons.adapter.ViewType) {

    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup): android.support.v7.widget.RecyclerView.ViewHolder = com.hanthienduc.kedditbysteps.feature.adapter.LoadingDelegateAdapter.TurnsViewHolder(parent)

    class TurnsViewHolder(parent: android.view.ViewGroup) : android.support.v7.widget.RecyclerView.ViewHolder(parent.inflate(com.hanthienduc.kedditbysteps.R.layout.news_item_loading)){

    }
}