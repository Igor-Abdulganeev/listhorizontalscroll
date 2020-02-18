package com.example.gorinih.klisthorizontalscroll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gorinih.klibscroll.interfaces.CreatorHeadItems

class TestHeadAdapterList :
    CreatorHeadItems {

    override fun instantiateItemHead(
        inflater: LayoutInflater,
        container: ViewGroup,
        position: Int
    ): View {
        val headView = inflater.inflate(R.layout.item_head, container, false)
        val txt: TextView = headView.findViewById(R.id.text_head_name)
        txt.text = TestHeadList.values()[position].caption
        return headView
    }

    override fun getCountHead(): Int = TestHeadList.values().size

    override fun getBodyNewPosition(positionItemHead: Int): Int = TestBodyList.get(positionItemHead)

}