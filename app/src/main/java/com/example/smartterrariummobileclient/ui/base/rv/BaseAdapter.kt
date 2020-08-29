package com.example.smartterrariummobileclient.ui.base.rv

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : BaseViewHolder<E>, E : Any>(
    private var dataSrc: List<E> = emptyList()
) : RecyclerView.Adapter<T>() {

    override fun getItemCount(): Int = dataSrc.size

    override fun onBindViewHolder(holder: T, position: Int) = holder.bind(dataSrc[position])

    fun setList(list: List<E>) {
        dataSrc = list
        this.notifyDataSetChanged()
    }

    fun addItem(entity: E) {
        dataSrc = dataSrc.plus(entity)
        this.notifyItemInserted(dataSrc.size - 1)
    }

    fun deleteItem(entity: E) {
        val index = dataSrc.indexOf(entity)
        if (index == -1) {
            return
        }
        dataSrc = dataSrc.minus(entity)
        this.notifyItemRemoved(index)
    }

    fun addAll(list: List<E>) {
        val startIndex = dataSrc.size
        dataSrc = dataSrc.plus(list)
        this.notifyItemRangeInserted(startIndex, dataSrc.size)
    }
}
