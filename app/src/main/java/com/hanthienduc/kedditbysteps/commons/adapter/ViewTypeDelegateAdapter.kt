package com.hanthienduc.kedditbysteps.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by hanth on 22/05/2017.
 */

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)

}