package com.gubatenko.comepay.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in T, in P>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T, presenter: P)

}