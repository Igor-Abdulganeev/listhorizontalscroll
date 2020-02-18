package com.example.gorinih.klibscroll.аdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gorinih.klibscroll.аdapter.CreatorBodyAdapter.BodyViewHolder
import com.example.gorinih.klibscroll.R
import com.example.gorinih.klibscroll.interfaces.CreatorBodyItems

class CreatorBodyAdapter(private val creatorBodyAdapter: CreatorBodyItems)
    : Adapter<BodyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.body_cell, parent, false)
        return BodyViewHolder(itemView)
    }

    override fun getItemCount(): Int = creatorBodyAdapter.getCountBody()

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.bind(position, creatorBodyAdapter)
    }

    //возвращаем номер элемена Head соответствующего указанному в параметре (position) номеру элемента Body
    fun getHeadPosition(position: Int): Int = creatorBodyAdapter.getHeadNewPosition(position)

    class BodyViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(
            position: Int,
            creatorBodyAdapter: CreatorBodyItems
        ) {
            val container = itemView as ViewGroup
            val itemView = creatorBodyAdapter.instantiateItemBody(
                LayoutInflater.from(itemView.context), container, position
            )
            container.removeAllViews()
            container.addView(itemView)
            this.itemView.requestLayout()
        }

    }
}