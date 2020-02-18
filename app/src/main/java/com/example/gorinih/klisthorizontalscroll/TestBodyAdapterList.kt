package com.example.gorinih.klisthorizontalscroll

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gorinih.klibscroll.interfaces.CreatorBodyItems

class TestBodyAdapterList : CreatorBodyItems {
    override fun instantiateItemBody(
        inflater: LayoutInflater,
        container: ViewGroup,
        position: Int
    ): View {
        val bodyView = inflater.inflate(R.layout.item_list,container,false)
        val txt: TextView = bodyView.findViewById(R.id.text_category_name)
        txt.text = TestBodyList.values()[position].item

        return bodyView
    }

    override fun getCountBody(): Int {
        return TestBodyList.values().size
    }

    override fun getHeadNewPosition(positionItemBody: Int): Int {
        return TestBodyList.values()[positionItemBody].id
    }
}