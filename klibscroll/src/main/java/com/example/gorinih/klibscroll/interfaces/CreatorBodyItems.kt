package com.example.gorinih.klibscroll.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/*
* Интерфейс обеспечивающий формирование адаптера с данными, содержащими второй уровень (Body) компонента
* переопределяются три метода
* [instantiateItemBody] - создает экземпляр Body
* [getCountBody] - возвращает количество элементов Body
* [getHeadNewPosition] - выдает первый номер элемента Head (первый уровень компонента) которому принадлежит указанный
* в параметре (positionItemBody) номер выбранного элемента Body
 */
interface CreatorBodyItems {
    fun instantiateItemBody(inflater: LayoutInflater, container: ViewGroup, position: Int): View
    fun getCountBody(): Int
    fun getHeadNewPosition(positionItemBody: Int): Int
}