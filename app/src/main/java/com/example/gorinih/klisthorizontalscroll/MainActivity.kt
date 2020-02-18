package com.example.gorinih.klisthorizontalscroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //создаем 2 списка
        scrollableList.setCreatorViewScrollableList(TestHeadAdapterList(),TestBodyAdapterList())
        createScrollableListSecond.setCreatorViewScrollableList(TestHeadAdapterList(),TestBodyAdapterList())
    }
}
