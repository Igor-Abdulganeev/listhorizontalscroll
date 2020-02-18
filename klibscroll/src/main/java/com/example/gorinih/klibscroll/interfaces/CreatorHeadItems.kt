package com.example.gorinih.klibscroll.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/*
* Интерфейс обеспечивающий формирование адаптера с данными, содержащими первый уровень (Head) компонента
* переопределяются три метода
* [instantiateItemHead] - создает экземпляр Head
* [getCountBody] - возвращает количество элементов Head
* [getBodyNewPosition] - выдает первый номер элемента Body (второй уровень компонента) которому принадлежит указанный
* в параметре (positionItemHead) номер выбранного элемента Head
 */

interface CreatorHeadItems {
    fun instantiateItemHead(inflater: LayoutInflater, container: ViewGroup, position: Int): View
    fun getCountHead(): Int
    fun getBodyNewPosition(positionItemHead: Int): Int
}