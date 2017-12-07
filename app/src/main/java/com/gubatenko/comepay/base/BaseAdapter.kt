package com.gubatenko.comepay.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

abstract class BaseAdapter<T, in P, VH : BaseViewHolder<T, P>>(private val presenter: P) : RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    abstract fun getViewType(position: Int): Int

    abstract fun vhProvider(view: View, viewType: Int): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            vhProvider(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position], presenter)

    override fun getItemViewType(position: Int): Int = getViewType(position)

    override fun getItemCount(): Int = items.size

    fun getItems(): ArrayList<T> = items

    fun addAll(list: List<T>) {
        items.addAll(list)
        notifyItemRangeInserted(items.size - list.size, list.size)
    }

    fun clear() {
        notifyItemRangeRemoved(0, items.size)
        items.clear()
    }

    fun update(item: T) {
        notifyItemChanged(items.indexOf(item))
    }

    fun remove(item: T) {
        notifyItemRemoved(items.indexOf(item))
        items.remove(item)
    }
}