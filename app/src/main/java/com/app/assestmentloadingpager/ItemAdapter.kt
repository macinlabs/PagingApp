package com.app.assestmentloadingpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemClickListener: OnItemClickListener) : PagingDataAdapter<Item, ItemViewHolder>(ItemComparator) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                itemClickListener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_roe, parent, false)
        return ItemViewHolder(view)
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Item) {
        itemView.findViewById<TextView>(R.id.id).text = item.id.toString()
        itemView.findViewById<TextView>(R.id.title).text = item.title
    }
}
