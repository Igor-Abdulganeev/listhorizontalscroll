package com.example.gorinih.klibscroll.аdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gorinih.klibscroll.аdapter.CreatorHeadAdapter.HeadViewHolder
import com.example.gorinih.klibscroll.R
import com.example.gorinih.klibscroll.interfaces.CreatorHeadItems

class CreatorHeadAdapter(private val creatorHeadAdapter: CreatorHeadItems) : Adapter<HeadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.head_cell, parent, false
        )
        return HeadViewHolder(itemView)
    }

    override fun getItemCount(): Int = creatorHeadAdapter.getCountHead()

    override fun onBindViewHolder(holder: HeadViewHolder, position: Int) {
        holder.bind(position, creatorHeadAdapter)
    }

    //возвращаем номер первого элемена Body соответствующего указанному в параметре (position) номеру Head
    fun getBodyPosition(position: Int): Int = creatorHeadAdapter.getBodyNewPosition(position)

    class HeadViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(
            position: Int,
            creatorHeadAdapter: CreatorHeadItems
        ) {
            val container = itemView as ViewGroup
            val itemView = creatorHeadAdapter.instantiateItemHead(
                LayoutInflater.from(itemView.context), container, position
            )
            container.removeAllViews()
            container.addView(itemView)
            this.itemView.requestLayout()
        }

    }
}